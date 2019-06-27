package com.example.moodchan

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moodchan.ViewHolder
import com.example.moodchan.RowModel

class ViewAdapter(private val list: List<RowModel>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("Adapter", "onCreateViewHolder")
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.chat_view_bot, parent, false)
        return ViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewHolder")
        holder.titleView.text = list[position].messageText

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