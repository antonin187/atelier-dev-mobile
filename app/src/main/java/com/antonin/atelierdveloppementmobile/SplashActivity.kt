package com.antonin.atelierdveloppementmobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed(Runnable {

            if (readSharedPref("Carte de fidélité") != "") {
                val newIntent = Intent(application, TabBarActivity::class.java)
                newIntent.putExtra("Prénom", readSharedPref("Prénom"))
                newIntent.putExtra("Nom", readSharedPref("Nom"))
                newIntent.putExtra("loyaltyNumber", readSharedPref("Carte de fidélité"))
                startActivity(newIntent)
            } else {
                val newIntent= Intent(application,CreateAccountActivity::class.java)
                startActivity(newIntent)
                finish()
            }


        },1500)
    }

    fun readSharedPref(key:String):String{
        val sharedPreferences: SharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key,"").toString()
    }
}