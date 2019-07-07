package com.example.moodchan

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moodchan.models.ChatLogModel
import com.example.moodchan.models.RowModel
import com.google.firebase.firestore.auth.User

class UserViewAdapter(private val list: List<ChatLogModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int) : Int{
        return if("Bot" == list[position].username.toString()) 0 else 1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("Adapter", "onCreateViewHolder")
        val layoutInflater = LayoutInflater.from(parent.context)
        val nowView: View
        Log.d("viewTypeCount",viewType.toString())
        if (viewType == 0) {
            nowView = layoutInflater.inflate(R.layout.chat_view_bot, parent, false)
            return BotViewHolder(nowView)
        }else{
            nowView = layoutInflater.inflate(R.layout.chat_view_user, parent, false)
            return UserViewHolder(nowView)

        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewHolder")
        Log.d("AdapterCheck2", list[position].toString())
        if (list[position].username == "Bot") {
            val holderBot: BotViewHolder = holder as BotViewHolder
            holderBot.messageView.text = list[position].userMassage
        } else {
            val holderUser: UserViewHolder = holder as UserViewHolder
            holderUser.messageView.text = list[position].userMassage
        }


    }

    override fun getItemCount(): Int {
        Log.d("Adapter", "getItemCount")
        return list.size
    }

}