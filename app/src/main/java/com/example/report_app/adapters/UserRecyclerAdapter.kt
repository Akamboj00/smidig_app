package com.eksamen.pgr208.adaptors

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.report_app.GlobalClass
import com.example.report_app.R
import com.google.firebase.referencecode.database.models.User


class UserRecyclerAdapter(
    val users: ArrayList<User>,
    val global: GlobalClass.Companion,
    val FN: TextView,
    val ID: TextView
) : RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRecyclerAdapter.ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(
            R.layout.user_card_layout,
            parent,
            false
        )

        return ViewHolder(viewHolder);
    }

    override fun getItemCount(): Int {
        return users.count()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserRecyclerAdapter.ViewHolder, position: Int) {
        holder.userName.text = users.get(position).firstName.toString()
        holder.userId.text = users.get(position).id.toString()
        holder.userButton.setOnClickListener{
            val user: User = users.get(position)
            global.user = user
            FN.text = "${GlobalClass.user!!.firstName} ${GlobalClass.user!!.lastName}"
            ID.text = "${GlobalClass.user!!.userId}"
        }
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var userName: TextView
        var userId: TextView
        var userButton: Button
        init {
            userName = itemView.findViewById(R.id.user_name)
            userId = itemView.findViewById(R.id.user_id)
            userButton = itemView.findViewById(R.id.user_button)
        }
    }
}