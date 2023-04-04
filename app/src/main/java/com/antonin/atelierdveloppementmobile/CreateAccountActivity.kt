package com.antonin.atelierdveloppementmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton

class CreateAccountActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val buttonQRCode=findViewById<ImageButton>(R.id.qrCodeButton)
        val buttonForm=findViewById<Button>(R.id.formButton)

        buttonQRCode.setOnClickListener(View.OnClickListener {
            val newIntent =  Intent(application, QRCodeScannerActivity::class.java)
            startActivity(newIntent)
        })

        buttonForm.setOnClickListener(View.OnClickListener {
            val newIntent =  Intent(application, FormInscriptionActivity::class.java)
            startActivity(newIntent)
        })


    }
}