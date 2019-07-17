package com.example.moodchan

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.fragment_signup.*
import com.example.moodchan.utils.UserAuth
import com.example.moodchan.utils.toastMake
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class SignupFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonSignUp.setOnClickListener {
            val activity = this.activity ?: return@setOnClickListener
            val user = UserAuth()
            val fm: FragmentManager = fragmentManager ?: return@setOnClickListener


            if (emailTextSignup.text.toString() == "" ||
                pwTextSignup.text.toString() == "" ||
                usernameSignup.text.toString() == ""
            ) {
                toastMake("Please Input", 0, -200, activity)
            } else {
                user.signUp(emailTextSignup.text.toString(), pwTextSignup.text.toString(), activity)
                user.signIn(emailTextSignup.text.toString(), pwTextSignup.text.toString(), usernameSignup.text.toString(),
                    true, activity, fm)


            }
        }
    }
}