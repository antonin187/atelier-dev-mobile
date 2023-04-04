package com.antonin.atelierdveloppementmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton

class CreateAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val buttonQRCode=findViewById<ImageButton>(R.id.qrCodeButton)

        buttonQRCode.setOnClickListener(View.OnClickListener {
            val newIntent =  Intent(application, QRCodeScannerActivity::class.java)
            startActivity(newIntent)
        })


    }
}