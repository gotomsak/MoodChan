package com.example.moodchan

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moodchan.models.RowModel

class BotViewAdapter(private val list: List<RowModel>) : RecyclerView.Adapter<BotViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BotViewHolder {
        Log.d("Adapter", "onCreateViewHolder")
        val chatView: View = LayoutInflater.from(parent.context).inflate(R.layout.chat_view_bot, parent, false)
        val userView: View = LayoutInflater.from(parent.context).inflate(R.layout.chat_view_user, parent, false)
        return BotViewHolder(chatView)
    }

    override fun onBindViewHolder(holder: BotViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewHolder")
        holder.messageView.text = list[position].messageText

//        holder.itemView.setOnClickListener {
//            listener.onClickRow(it, list[position])
//        }
    }

    override fun getItemCount(): Int {
        Log.d("Adapter", "getItemCount")
        return list.size
    }

//    interface ListListener {
//        fun onClickRow(tappedView: View, rowModel: RowModel)
//    }
}