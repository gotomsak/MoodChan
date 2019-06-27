package com.example.moodchan

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleView: TextView = itemView.findViewById(R.id.chatBotSend)

}