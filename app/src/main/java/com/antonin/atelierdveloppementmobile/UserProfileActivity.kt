package com.antonin.atelierdveloppementmobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class UserProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        showBack()
        setHeaderText("Compte")

        val editTextFirstName=findViewById<EditText>(R.id.firstnameEditText)
        editTextFirstName.setText(readSharedPref("Prénom"))
        val editTextLastName=findViewById<EditText>(R.id.lastnameEditText)
        editTextLastName.setText(readSharedPref("Nom"))
        val editTextEmail=findViewById<EditText>(R.id.emailEditText)
        editTextEmail.setText(readSharedPref("Email"))
        val editTextAddress=findViewById<EditText>(R.id.addressEditText)
        editTextAddress.setText(readSharedPref("Adresse"))
        val editTextCP=findViewById<EditText>(R.id.cpEditText)
        editTextCP.setText(readSharedPref("Code Postal"))
        val editTextCity=findViewById<EditText>(R.id.cityEditText)
        editTextCity.setText(readSharedPref("Ville"))

        val submitChangesButton = findViewById<Button>(R.id.submitChangesButton)

        submitChangesButton.setOnClickListener(View.OnClickListener {

            val txt=editTextFirstName.text.toString()+
                    " / "+editTextLastName.text.toString()+
                    " / "+editTextEmail.text.toString()+" / "+
                    " / "+editTextAddress.text.toString()+" / "+
                    " / "+editTextCP.text.toString()+" / "+
                    editTextCity.text.toString()
            writeSharedPref("Prénom",editTextFirstName.text.toString());
            writeSharedPref("Nom",editTextLastName.text.toString());
            writeSharedPref("Email",editTextEmail.text.toString());
            writeSharedPref("Adresse",editTextAddress.text.toString());
            writeSharedPref("Code Postal",editTextCP.text.toString());
            writeSharedPref("Ville",editTextCity.text.toString());

            if (readSharedPref("Carte de fidélité") != "") {
                val newIntent = Intent(application, TabBarActivity::class.java)
                newIntent.putExtra("Prénom", readSharedPref("Prénom"))
                newIntent.putExtra("Nom", readSharedPref("Nom"))
                newIntent.putExtra("loyaltyNumber", readSharedPref("Carte de fidélité"))
                finish()
                startActivity(newIntent)
            }

            finish()

        })

    }

    fun writeSharedPref(key:String,value:String){
        val sharedPreferences: SharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE)
        val editor =sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }
    fun readSharedPref(key:String):String{
        val sharedPreferences: SharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key,"").toString()
    }
}