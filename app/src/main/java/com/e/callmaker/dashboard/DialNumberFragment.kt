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
import kotlinx.android.synthetic.main.fragment_dialing_number.*

/**
 * A simple [Fragment] subclass.
 */
class DialNumberFragment : Fragment(), View.OnClickListener {

    private val MY_PERMISSIONS_REQUEST_CALL_PHONE = 1234

    var number : String? = ""

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
        btn0?.setOnClickListener (this)
        btn1?.setOnClickListener (this)
        btn2?.setOnClickListener (this)
        btn3?.setOnClickListener (this)
        btn4?.setOnClickListener (this)
        btn5?.setOnClickListener (this)
        btn6?.setOnClickListener (this)
        btn7?.setOnClickListener (this)
        btn8?.setOnClickListener (this)
        btn9?.setOnClickListener (this)
        btnCl?.setOnClickListener (this)
        makeCall?.setOnClickListener (this)
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

    override fun onClick(v: View?) {
        when (v!!.id){
            R.id.btn0 -> {
                if(number?.length == 10){
                    return
                }else{
                    number += "0"
                    numText?.text = number
                }

            }
            R.id.btn1 -> {
                if(number?.length == 10){
                    return
                }else{
                    number += "1"
                    numText?.text = number
                }

            }
            R.id.btn2 -> {
                if(number?.length == 10){
                    return
                }else{
                    number += "2"
                    numText?.text = number
                }

            }
            R.id.btn3 -> {
                if(number?.length == 10){
                    return
                }else{
                    number += "3"
                    numText?.text = number
                }
            }
            R.id.btn4 -> {
                if(number?.length == 10){
                    return
                }else{
                    number += "4"
                    numText?.text = number
                }
            }
            R.id.btn5 -> {
                if(number?.length == 10){
                    return
                }else{
                    number += "5"
                    numText?.text = number
                }
            }
            R.id.btn6 -> {
                if(number?.length == 10){
                    return
                }else{
                    number += "6"
                    numText?.text = number
                }
            }
            R.id.btn7 -> {
                if(number?.length == 10){
                    return
                }else{
                    number += "7"
                    numText?.text = number
                }
            }
            R.id.btn8 -> {
                if(number?.length == 10){
                    return
                }else{
                    number += "8"
                    numText?.text = number
                }
            }
            R.id.btn9 -> {
                if(number?.length == 10){
                    return
                }else{
                    number += "9"
                    numText?.text = number
                }
            }
            R.id.btnCl -> {
                number = ""
                numText?.text = ""
            }
            R.id.makeCall -> {
                if (number?.length == 10){
                    callMakingEvent(numText?.text.toString())
                } else{
                    Toast.makeText(activity,"Provided number is invalid", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }


}
