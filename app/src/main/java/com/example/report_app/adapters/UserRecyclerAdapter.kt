package com.eksamen.pgr208.adaptors

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.report_app.R
import java.util.*
import kotlin.collections.ArrayList

class UserRecyclerAdapter(val arrayList: ArrayList<UserTemplate>) : RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRecyclerAdapter.ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.user_card_layout, parent, false)
        return ViewHolder(viewHolder);
    }
    override fun getItemCount(): Int {
        return arrayList.count()
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserRecyclerAdapter.ViewHolder, position: Int) {
        holder.userName.text = arrayList.get(position).firstName
        holder.userId.text = arrayList.get(position).id.toString()

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

class UserTemplate {
    var firstName:String?=null
    var lastName:String?=null
    var id:Int?=null

    constructor(){}

    constructor(firstName:String, lastName:String, id:Int)
    {
        this.firstName = firstName
        this.lastName = lastName
        this.id = id
    }

}