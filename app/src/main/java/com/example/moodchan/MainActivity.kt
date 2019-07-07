package com.example.moodchan
import android.content.ContentValues.TAG
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.example.moodchan.LoginFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
//import android.R
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        if (savedInstanceState == null) {
            if (auth.currentUser != null) {
                Log.d(TAG, auth.currentUser?.uid.toString())
                val transaction = supportFragmentManager.beginTransaction()
                transaction.add(R.id.detailContainer, ChatFragment())
                .commit()
            } else {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.add(R.id.detailContainer, LoginFragment())
                .commit()
            }
        }
    }

}
