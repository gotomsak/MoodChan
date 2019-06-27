package com.example.moodchan

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_login.*
import java.io.LineNumberReader


class ChatFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val chatlog: MutableList<String> = mutableListOf()
        super.onViewCreated(view, savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser
        val list = listOf(RowModel("Hello"), RowModel("Hoge"))
        val adapter = ViewAdapter(list)
        chatRecycler.layoutManager = LinearLayoutManager(this.activity)
        chatRecycler.adapter = adapter

        user?.let {
            val name = user.displayName
            val email = user.email
            val emailVerified = user.isEmailVerified
            val uid = user.uid
            Log.d(TAG, name)

        }

        buttonSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val fm: FragmentManager = fragmentManager ?: return@setOnClickListener
            fm.beginTransaction()
                .replace(R.id.detailContainer, LoginFragment())
                .commit()
        }

        buttonMessageSend.setOnClickListener{
            chatlog.add(messageChatSend.toString())

        }

    }
}


//class RowModel {
//    var text: String = ""
//
//}
//class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//    val titleView: TextView = itemView.findViewById(R.id.chatBotSend)
//
//}


