package com.example.moodchan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.moodchan.LoginFragment
import kotlinx.android.synthetic.main.fragment_login.*
//import android.R
import kotlinx.android.synthetic.main.activity_main.*




//var db = FirebaseFirestore.getInstance()
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        LoginFragment: loginFragment = new LoginFragment()
//        var login = LoginFragment()
//        val transaction = supportFragmentManager.beginTransaction()
//        // 新しく追加を行うのでaddを使用します
//        // 他にも、よく使う操作で、replace removeといったメソッドがあります
//        // メソッドの1つ目の引数は対象のViewGroupのID、2つ目の引数は追加するfragment
//        transaction.add(R.id.nyan, fragment)
//        // 最後にcommitを使用することで変更を反映します
//        transaction.commit()
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.detailContainer, LoginFragment.newInstance() )
            transaction.commit()
        }



    }
}
