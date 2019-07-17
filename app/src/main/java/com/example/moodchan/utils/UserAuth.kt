package com.example.moodchan.utils

import android.content.ContentValues

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.moodchan.ChatFragment
import com.example.moodchan.R
import com.example.moodchan.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class UserAuth {


    fun signUp(email: String, password: String,  activity: FragmentActivity){
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity){ task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")

                    val user = auth.currentUser
                    Log.d("signup email", user?.email.toString())
                    toastMake("signup: success",0, -200, activity)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    toastMake("sign up field: success",0, -200, activity)
                }

            }

    }


    fun signIn(email: String, password: String, username: String?,
               signupFlag: Boolean, activity: FragmentActivity, fm: FragmentManager){
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    //toastMake("signIn成功",0,-200, activity)
                    if (signupFlag){
                        dbUserSave(username, activity)
                    }
                    fm.beginTransaction()
                        .replace(R.id.detailContainer, ChatFragment())
                        .commit()
                } else {
                    toastMake("signIn失敗",0,-200, activity)
                }
            }
    }

    fun dbUserSave(username: String?, activity: FragmentActivity){
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val uid = user?.uid.toString()
        val email = user?.email.toString()

        toastMake(email,0, -200, activity)
        Log.d("dbUserSaveuid",uid)
        Log.d("dbUserSaveEmail",email)

        val userM = UserModel(username)
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(uid).set(userM)
            .addOnSuccessListener {
                toastMake("save user",0,-200,  activity)
            }
            .addOnFailureListener{
                toastMake("failed save",0,-200,  activity)
            }
    }

}
