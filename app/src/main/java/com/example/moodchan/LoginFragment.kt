package com.example.moodchan

import android.content.Context
import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.fragment_login.*
//import android.R
import kotlinx.android.synthetic.*
import java.io.IOError
import java.io.IOException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {


    private fun toastMake(message: String, x: Int, y: Int) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        // 位置調整
        toast.setGravity(Gravity.CENTER, x, y)
        toast.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val auth = FirebaseAuth.getInstance()

        buttonLogin.setOnClickListener {
            if (emailTextLogin.text.toString() == "" || pwTextLogin.text.toString() == "") {
                toastMake("入力してください", 0, -200)
            } else {
                val email = emailTextLogin.text.toString()
                val password = pwTextLogin.text.toString()
                val activity = this.activity ?: return@setOnClickListener
                val fm: FragmentManager = fragmentManager ?: return@setOnClickListener

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity) { task ->
                        if (task.isSuccessful) {
                            //toastMake("login成功",0,-200)

                            fm.beginTransaction()
                                .replace(R.id.detailContainer, ChatFragment())
                                .commit()
                        } else {
                            toastMake("login失敗",0,-200)
                        }
                    }
            }
        }

        buttonSignUpView.setOnClickListener {

            val fm: FragmentManager = fragmentManager ?: return@setOnClickListener
            fm.beginTransaction()
                .replace(R.id.detailContainer, SignupFragment())
                .addToBackStack("loginFragment")
                .commit()
        }


    }



}
