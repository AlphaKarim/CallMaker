package com.e.callmaker.dashboard


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.e.callmaker.R

/**
 * A simple [Fragment] subclass.
 */
class DialNumberFragment : Fragment() {

    private val MY_PERMISSIONS_REQUEST_CALL_PHONE = 1234
    var b1 : Button? = null
    var b2 : Button? = null
    var b3 : Button? = null
    var b4 : Button? = null
    var b5 : Button? = null
    var b6 : Button? = null
    var b7 : Button? = null
    var b8 : Button? = null
    var b9 : Button? = null
    var b0 : Button? = null
    var b_clear : Button? = null
    var numText : TextView? = null
    var number : String? = ""
    var makeCall : LinearLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dialing_number, container, false)
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
    }

    private fun initialization(){
        b0 = view?.findViewById(R.id.btn0)
        b1 = view?.findViewById(R.id.btn1)
        b2 = view?.findViewById(R.id.btn2)
        b3 = view?.findViewById(R.id.btn3)
        b4 = view?.findViewById(R.id.btn4)
        b5 = view?.findViewById(R.id.btn5)
        b6 = view?.findViewById(R.id.btn6)
        b7 = view?.findViewById(R.id.btn7)
        b8 = view?.findViewById(R.id.btn8)
        b9 = view?.findViewById(R.id.btn9)
        b_clear = view?.findViewById(R.id.btnCl)
        numText = view?.findViewById(R.id.numText)
        makeCall = view?.findViewById(R.id.makeCall)

        b0?.setOnClickListener {
            if(number?.length == 10){
                return@setOnClickListener
            }else{
                number += "0"
                numText?.text = number
            }

        }

        b1?.setOnClickListener {
            if(number?.length == 10){
                return@setOnClickListener
            }else{
                number += "1"
                numText?.text = number
            }

        }

        b2?.setOnClickListener {
            if(number?.length == 10){
                return@setOnClickListener
            }else{
                number += "2"
                numText?.text = number
            }

        }

        b3?.setOnClickListener {
            if(number?.length == 10){
                return@setOnClickListener
            }else{
                number += "3"
                numText?.text = number
            }

        }

        b4?.setOnClickListener {
            if(number?.length == 10){
                return@setOnClickListener
            }else{
                number += "4"
                numText?.text = number
            }

        }

        b5?.setOnClickListener {
            if(number?.length == 10){
                return@setOnClickListener
            }else{
                number += "5"
                numText?.text = number
            }

        }

        b6?.setOnClickListener {
            if(number?.length == 10){
                return@setOnClickListener
            }else{
                number += "6"
                numText?.text = number
            }

        }

        b7?.setOnClickListener {
            if(number?.length == 10){
                return@setOnClickListener
            }else{
                number += "7"
                numText?.text = number
            }

        }

        b8?.setOnClickListener {
            if(number?.length == 10){
                return@setOnClickListener
            }else{
                number += "8"
                numText?.text = number
            }

        }

        b9?.setOnClickListener {
            if(number?.length == 10){
                return@setOnClickListener
            }else{
                number += "9"
                numText?.text = number
            }

        }

        b_clear?.setOnClickListener {
            number = ""
            numText?.text = ""
        }
        makeCall?.setOnClickListener {
            if (number?.length == 10){
                callMakingEvent(numText?.text.toString())
            } else{
                Toast.makeText(activity,"Provided number is invalid", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun callMakingEvent(string:String) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$string")
        if (activity?.let { ActivityCompat.checkSelfPermission(it, Manifest.permission.CALL_PHONE) } != PackageManager.PERMISSION_GRANTED) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    MY_PERMISSIONS_REQUEST_CALL_PHONE)
            }
        }
        startActivity(callIntent)
    }


}
