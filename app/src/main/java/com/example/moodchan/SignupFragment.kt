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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val auth = FirebaseAuth.getInstance()

        buttonSignUp.setOnClickListener {
            val activity = this.activity ?: return@setOnClickListener
            auth.createUserWithEmailAndPassword(emailTextSignup.text.toString(),pwTextSignup.text.toString())
                .addOnCompleteListener(activity){ task ->

                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")

                        val user = auth.currentUser
                        Log.d(TAG, user.toString())

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)

                    }

                }
            val user = auth.currentUser
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(usernameSignup.text.toString())
                .build()
            Log.d(TAG,  user?.email.toString())
            user?.updateProfile(profileUpdates)
                ?.addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Log.d(TAG, "User profile updated.")
                    } else {
                        Log.d(TAG, "update failed.")
                    }
                }

        }


    }
}