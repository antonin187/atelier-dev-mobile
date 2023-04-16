package com.antonin.atelierdveloppementmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.log

class FormInscriptionActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_inscription)

        val createUserButton = findViewById<Button>(R.id.createUserButton)
        val loyaltyCardNumber = findViewById<EditText>(R.id.fidelityCardEditText);


        createUserButton.setOnClickListener(View.OnClickListener {
            Log.d("LOYALITY", loyaltyCardNumber.text.toString())
            val newIntent = Intent(application, UserProfileActivity::class.java)
            newIntent.putExtra("loyaltyNumber", loyaltyCardNumber.text.toString())
            startActivity(newIntent)
        })
    }
}