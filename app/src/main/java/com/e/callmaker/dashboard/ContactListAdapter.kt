package com.e.callmaker.dashboard

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.e.callmaker.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.contact_dialog.*
import kotlinx.android.synthetic.main.contact_item.view.*
import java.util.ArrayList

class ContactListAdapter(private val activity: Context, private val list: ArrayList<ContactDetails>) :
    RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.contact_item,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = list[position].name/*+"\n"+list[position].name*/
        val number = list[position].number
        val bottomSheetDialog = BottomSheetDialog(activity)
        bottomSheetDialog.window?.setDimAmount(0.8f)
        bottomSheetDialog.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)/*
        bottomSheetDialog.window?.addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND)*/
        bottomSheetDialog.setContentView(R.layout.contact_dialog)
        holder.contactLayout.setOnClickListener {
            bottomSheetDialog.show()
            Log.e("contactNumber",number)
            bottomSheetDialog.contactName.text = list[position].name
            bottomSheetDialog.contactNumber.text = list[position].number
            bottomSheetDialog.contactMakeCall.setOnClickListener {
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:$number")
                if (activity.let { ActivityCompat.checkSelfPermission(it, Manifest.permission.CALL_PHONE) } != PackageManager.PERMISSION_GRANTED) {
                    activity.let {
                        ActivityCompat.requestPermissions(
                            it as Activity,
                            arrayOf(Manifest.permission.CALL_PHONE),
                            1092)
                    }
                }
                activity.startActivity(callIntent)
                bottomSheetDialog.dismiss()
            }
        }
    }


    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val textView: TextView = view.contactTextView
        val contactLayout: LinearLayout = view.contactLayout
    }

}
