package com.e.callmaker.dashboard


import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.e.callmaker.R
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = getCallDetails()
        val adapterList = ContactListAdapter(activity!!,list)
        contactRv.apply {
            adapter = adapterList
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun getCallDetails(): ArrayList<ContactDetails> {
        val contactDetails = ArrayList<ContactDetails>()
        Thread(Runnable {
            val contentUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            try {
                val cursor = context!!.contentResolver.query(contentUri,null,null,null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC")
                Log.e("CursorCount",""+cursor?.count)
                val number = cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val name = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

                if(cursor.moveToFirst()){
                    do {

                        val contactNumber = cursor.getString(number)
                        val contactName = cursor.getString(name)
                        contactDetails.add(
                            ContactDetails(
                            contactName,contactNumber)
                        )

                    }while (cursor.moveToNext())
                    Log.e("ListCount",""+contactDetails.size)
                }
                cursor.close()

            }catch (e: SecurityException){
                Toast.makeText(activity,"permission Denied", Toast.LENGTH_SHORT).show()
            }
        }).start()
        return contactDetails

    }


}
