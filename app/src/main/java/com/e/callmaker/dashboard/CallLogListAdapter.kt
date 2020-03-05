package com.e.callmaker.dashboard

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.e.callmaker.R
import kotlinx.android.synthetic.main.call_log_row_item.view.*

class CallLogListAdapter(val context:Context, val list: ArrayList<CallDetails>): RecyclerView.Adapter<CallLogListAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.call_log_row_item,parent,false))
    }


    override fun getItemCount(): Int {
        Log.e("getItemCount",""+list.size)
        return list.size
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.textView.text =  "Caller:${list[position].name}\n"+
                "Phone number:${list[position].number}\n"+
                "Call Duration:${list[position].duration}\n"+
                "Call Type:${list[position].type}\n"+
                "Call Time:${list[position].dayTime}\n"
    }
    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textView: AppCompatTextView = view.list_row_item
    }

}