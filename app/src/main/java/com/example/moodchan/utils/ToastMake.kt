package com.example.moodchan.utils
import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.FragmentActivity



fun toastMake(message: String, x: Int, y: Int, activity:FragmentActivity) {

    val toast = Toast.makeText(activity, message, Toast.LENGTH_LONG)
    // 位置調整
    toast.setGravity(Gravity.CENTER, x, y)
    toast.show()
}

