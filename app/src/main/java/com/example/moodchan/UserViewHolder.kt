package com.example.moodchan


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val messageView: TextView = itemView.findViewById(R.id.chatUserSend)

}