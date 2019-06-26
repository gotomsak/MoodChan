package com.example.moodchan

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_login.*


class ChatFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser
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

    }
}