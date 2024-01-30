package com.example.durbarnotificationtest

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.messaging

class MainActivity : AppCompatActivity() {
    private lateinit var etToken: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etToken = findViewById(R.id.etToken)

        Firebase.messaging.subscribeToTopic("weather")
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                Log.d("notifi", msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("notification", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
          //  val msg = getString(R.string.msg_token_fmt, token)
           System.out.println(token)
            Toast.makeText(this,"device rg token is"+ token, Toast.LENGTH_SHORT).show()

            etToken.setText(token)
        })
    }



}