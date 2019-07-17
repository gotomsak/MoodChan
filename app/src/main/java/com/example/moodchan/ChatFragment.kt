package com.example.moodchan

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moodchan.models.ChatLogModel
import com.example.moodchan.models.RowModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_chat.*

import java.util.*


class ChatFragment: Fragment(){
    private val listUser = mutableListOf<ChatLogModel>()
    lateinit var adapter: UserViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val db = FirebaseFirestore.getInstance()

        chatUserRecycler.layoutManager = LinearLayoutManager(this.activity)
        adapter = UserViewAdapter(listUser)
        chatUserRecycler.adapter = adapter
        db.collection("chatlogs").document(user?.uid.toString())
            .collection("textlog").addSnapshotListener{ snapshot ,e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                val dbList = mutableListOf<ChatLogModel>()
                for (doc in snapshot!!){
                    dbList.add(
                        ChatLogModel(
                            doc.data.get("username").toString(),
                            doc.data.get("userMessage").toString(),
                            doc.data.get("chatId").toString())
                    )
                    Log.d("getlist", doc.data.get("userMassage").toString())
                }
                val addList = dbList - listUser
                Log.i("debug", addList.toString())

                addList.forEach {
                    listUser.add(it)
                    adapter.notifyDataSetChanged()
                    chatUserRecycler.smoothScrollToPosition(adapter.itemCount - 1)
                }
            }


        buttonSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val fm: FragmentManager = fragmentManager ?: return@setOnClickListener
            fm.beginTransaction()
                .replace(R.id.detailContainer, LoginFragment())
                .commit()
        }

        messageChatSend.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val str = s ?: return
                buttonMessageSend.isEnabled = !str.isBlank()
            }
        })

        buttonMessageSend.setOnClickListener{
            if(messageChatSend.text.toString() == ""){
                return@setOnClickListener
            }

            val uid = user?.uid.toString()
            db.collection("users").document(uid).get().addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    Log.d("nameGet", "success")
                    val date = Date()
                    val dateF: String = DateFormat.format("yyyy_MM_dd kk:mm:ss", date).toString()
                    val chatLogSize:Int = listUser.size + 1
                    Log.i("kusoga", chatLogSize.toString())
                    val chatlog = ChatLogModel(task.result?.data?.get("username").toString(),
                        messageChatSend.text.toString(), chatLogSize.toString())
                    db.collection("chatlogs").document(uid)
                        .collection("textlog").document(dateF).set(chatlog)
                        .addOnSuccessListener {
                            Log.d(TAG, "addDataSecondCollection")
                            messageChatSend.setText("")
                        }
                        .addOnFailureListener {
                                e -> Log.d(TAG, "Error adding document" + e)
                        }

                } else {
                    Log.d("nameGet", "failed")
                }

            }


        }

    }
}




