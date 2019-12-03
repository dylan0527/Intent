package com.example.intent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSend.setOnClickListener{
            sendMessage()
        }
        buttonDialer.setOnClickListener{
            callMe()
        }
    }

    private fun sendMessage(){

        val message = editTextMessage.text.toString()
        val lucky = editTextLucky.text.toString().toInt()
        //Explicit Intent
        val intent = Intent(this, SecondActivity::class.java)
        //putExtra to the intent
        intent.putExtra(EXTRA_MESSAGE,message)
        intent.putExtra(EXTRA_LUCKY,lucky)
        //start the second activity.With no return value
        //startActivity(intent)
        //start the second activity with return value
        startActivityForResult(intent , REQUEST_CODE)

    }

    private fun callMe(){
        //geo:0123,1234 (LAtitude / longtitude
        //http://aosjfas ( website)
        val phone = "tel:0124040482"
        //An implicit Intent
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(phone))
        //Validate app package manager
        if(intent.resolveActivity(packageManager)!=null) {
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                textViewReply.text = String.format("%s %s",getString(R.string.reply),data?.getStringExtra(EXTRA_REPLY))
            }

        }

    }
    companion object{
        const val EXTRA_MESSAGE = "com.example.intent.MESSAGE"
        const val EXTRA_LUCKY = "com.example.intent.LUCKY"
        const val REQUEST_CODE = 1
        const val EXTRA_REPLY = "com.example.intent.REPLY"
    }
}
