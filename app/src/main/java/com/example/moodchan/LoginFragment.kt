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
import com.example.moodchan.utils.UserAuth
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.fragment_login.*
//import android.R
import kotlinx.android.synthetic.*
import java.io.IOError
import java.io.IOException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.moodchan.utils.toastMake
import kotlinx.android.synthetic.main.fragment_signup.*


class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonLogin.setOnClickListener {
            val activity = this.activity ?: return@setOnClickListener
            if (emailTextLogin.text.toString() == "" || pwTextLogin.text.toString() == "") {
                toastMake("Please Input", 0, -200, activity)
            } else {
                val email = emailTextLogin.text.toString()
                val password = pwTextLogin.text.toString()

                val fm: FragmentManager = fragmentManager ?: return@setOnClickListener
                val user = UserAuth()

                user.signIn(email, password, null,false, activity, fm)

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
