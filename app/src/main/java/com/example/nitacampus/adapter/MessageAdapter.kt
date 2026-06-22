package com.example.nitacampus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nitacampus.R
import com.example.nitacampus.model.MessageModel

class MessageAdapter(
    private val messageList: ArrayList<MessageModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val USER_VIEW = 1
        const val AI_VIEW = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (messageList[position].isUser) USER_VIEW else AI_VIEW
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == USER_VIEW) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user, parent, false)
            UserViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ai, parent, false)
            AiViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val message = messageList[position]

        if (holder is UserViewHolder) {
            holder.tvMessage.text = message.message
        } else if (holder is AiViewHolder) {
            holder.tvMessage.text = message.message
        }
    }

    override fun getItemCount(): Int = messageList.size

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
    }

    class AiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
    }
}