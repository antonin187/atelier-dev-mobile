package com.antonin.atelierdveloppementmobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class FormInscriptionActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_inscription)

        showBack()
        setHeaderText(getString(R.string.create_account))

        val createUserButton = findViewById<Button>(R.id.createUserButton)
        val loyaltyCardNumber = findViewById<EditText>(R.id.fidelityCardEditText);
        val editTextFirstName=findViewById<EditText>(R.id.firstnameEditText)
        val editTextLastName=findViewById<EditText>(R.id.lastnameEditText)
        val editTextAddress=findViewById<EditText>(R.id.addressEditText)
        val editTextEmail=findViewById<EditText>(R.id.emailEditText)
        val editTextCP=findViewById<EditText>(R.id.cpEditText)
        val editTextCity=findViewById<EditText>(R.id.cityEditText)

        val isSetFromQrCode:Boolean = (intent.extras?.get("qrCode") ?: false) as Boolean

        if(isSetFromQrCode){
            val firstName = intent.extras?.getString("firstName") ?: ""
            val lastName = intent.extras?.getString("lastName") ?: ""
            val email = intent.extras?.getString("email") ?: ""
            val address = intent.extras?.getString("address") ?: ""
            val zipcode = intent.extras?.getString("zipcode") ?: ""
            val city = intent.extras?.getString("city") ?: ""
            val cardRef = intent.extras?.getString("cardRef") ?: ""
            editTextFirstName.setText(firstName)
            editTextLastName.setText(lastName)
            editTextEmail.setText(email)
            editTextAddress.setText(address)
            editTextCP.setText(zipcode)
            editTextCity.setText(city)
            loyaltyCardNumber.setText(cardRef)
        } else {
            editTextFirstName.setText(readSharedPref("Prénom"))
            editTextLastName.setText(readSharedPref("Nom"))
            editTextEmail.setText(readSharedPref("Email"))
            editTextAddress.setText(readSharedPref("Adresse"))
            editTextCP.setText(readSharedPref("Code Postal"))
            editTextCity.setText(readSharedPref("Ville"))
            loyaltyCardNumber.setText(readSharedPref("Carte de fidélité"))
        }



        createUserButton.setOnClickListener(View.OnClickListener {

            val txt=editTextFirstName.text.toString()+
                    " / "+editTextLastName.text.toString()+
                    " / "+editTextEmail.text.toString()+" / "+
                    " / "+editTextAddress.text.toString()+" / "+
                    " / "+editTextCP.text.toString()+" / "+
                    " / "+editTextCity.text.toString()+" / "+
                    loyaltyCardNumber.text.toString()
            writeSharedPref("Prénom",editTextFirstName.text.toString());
            writeSharedPref("Nom",editTextLastName.text.toString());
            writeSharedPref("Email",editTextEmail.text.toString());
            writeSharedPref("Adresse",editTextAddress.text.toString());
            writeSharedPref("Code Postal",editTextCP.text.toString());
            writeSharedPref("Ville",editTextCity.text.toString());
            writeSharedPref("Carte de fidélité",loyaltyCardNumber.text.toString());


            val newIntent = Intent(application, TabBarActivity::class.java)
            newIntent.putExtra("Prénom", editTextFirstName.text.toString())
            newIntent.putExtra("Nom", editTextLastName.text.toString())
            newIntent.putExtra("loyaltyNumber", loyaltyCardNumber.text.toString())
            startActivity(newIntent)
        })
    }

    fun writeSharedPref(key:String,value:String){
        val sharedPreferences: SharedPreferences= getSharedPreferences("account",Context.MODE_PRIVATE)
        val editor =sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }
    fun readSharedPref(key:String):String{
        val sharedPreferences: SharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key,"").toString()
    }
}