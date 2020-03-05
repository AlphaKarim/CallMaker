package com.e.callmaker.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.callmaker.R
import kotlinx.android.synthetic.main.contact_item.view.*
import java.util.ArrayList

class ContactListAdapter(val activity: Context, val list: ArrayList<ContactDetails>) :
    RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.contact_item,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: ContactListAdapter.ViewHolder, position: Int) {
        holder.textView.text = list[position].number+"\n"+list[position].name
    }


    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val textView = view.contactTextView
    }

}
