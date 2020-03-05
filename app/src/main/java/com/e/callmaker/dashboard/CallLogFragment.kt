package com.e.callmaker.dashboard


import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.provider.CallLog
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.e.callmaker.R
import kotlinx.android.synthetic.main.fragment_call_log.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class CallLogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData(){
        val list = getCallDetails(activity)
        val adapterList = CallLogListAdapter(activity!!,list)
        listView.apply {
            adapter = adapterList
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun getCallDetails(activity: Context?): ArrayList<CallDetails> {
        val callDetails = ArrayList<CallDetails>()
        Thread(Runnable {
            val contentUri = CallLog.Calls.CONTENT_URI
            try {
                val cursor = context!!.contentResolver.query(contentUri,null,null,null,CallLog.Calls.DATE + " DESC")
                Log.e("CursorCount",""+cursor?.count)
                val nameUri = cursor!!.getColumnIndex(CallLog.Calls.CACHED_LOOKUP_URI)
                val number = cursor!!.getColumnIndex(CallLog.Calls.NUMBER)
                val duration = cursor.getColumnIndex(CallLog.Calls.DURATION)
                val date = cursor.getColumnIndex(CallLog.Calls.DATE)
                val type = cursor.getColumnIndex(CallLog.Calls.TYPE)

                if(cursor.moveToFirst()){
                    do {
                        val callType = when(cursor.getInt(type)){
                            CallLog.Calls.INCOMING_TYPE -> "Incoming"
                            CallLog.Calls.OUTGOING_TYPE -> "Outoing"
                            CallLog.Calls.MISSED_TYPE -> "Missed"
                            CallLog.Calls.REJECTED_TYPE -> "Rejected"
                            else -> "Not Defined"
                        }
                        val phoneNumber = cursor.getString(number)
                        val callerNameUri = cursor.getString(nameUri)
                        val callDate = cursor.getString(date)
                        val callDayTime = Date(callDate.toLong()).toString()
                        val callDuration = cursor.getString(duration)
                        Thread(Runnable {
                            callDetails.add(CallDetails(
                                getCallerNameUri(callerNameUri),phoneNumber,callDuration,callType,callDayTime))//getCallerNameUri(callerNameUri),
                        }).start()

                    }while (cursor.moveToNext())
                    Log.e("ListCount",""+callDetails.size)
                }
                cursor.close()

            }catch (e: SecurityException){
                Toast.makeText(activity,"permission Denied",Toast.LENGTH_SHORT).show()
            }
        }).start()
        return callDetails

    }

    private fun getCallerNameUri(callerNameUri: String?): String {
        var name = ""
             try {
                 if (callerNameUri != null){
                     val cursor = activity!!.contentResolver.query(Uri.parse(callerNameUri),null,null,null,null)
                     if ((cursor?.count ?:0)>0){
                         while (cursor != null && cursor.moveToNext()){
                             name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                         }
                     }
                     cursor?.close()
                 }else{
                     name = "Not a Contact!"
                 }
             }catch (e:Exception){
                 name = "Not a Contact!"
             }
        return name
    }

}
