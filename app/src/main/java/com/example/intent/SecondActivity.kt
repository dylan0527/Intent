package com.example.intent

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
        val lucky = intent.getIntExtra(MainActivity.EXTRA_LUCKY,0)

        textViewMessage.text = String.format("%s %s",getString(R.string.message),message)
        textViewLucky.text = String.format("%s %s",getString(R.string.lucky_number),lucky.toString())

        buttonDone.setOnClickListener{
            val reply = editTextReply.text.toString()
            val intent = getIntent() //who call me?
            if(!editTextReply.text.isEmpty()) {
                intent.putExtra(MainActivity.EXTRA_REPLY, reply)
                setResult(Activity.RESULT_OK, intent)
                //terminnate the activity
            }else{
                setResult(Activity.RESULT_CANCELED)
            }
            finish()

        }

    }
}

