package com.antonin.atelierdveloppementmobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView


class TabBarActivity : BaseActivity() {

    var tabCarteFragment=TabCarteFragment.newInstance("","", "")
    val tabOffresFragment=TabOffresFragment.newInstance("","")
    val tabMagasinsFragment=TabMagasinsFragment.newInstance("","")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*var prenom = intent.extras?.getString("Prénom") ?: ""
        var nom = intent.extras?.getString("Nom") ?: ""*/
        var prenom = readSharedPref("Prénom") ?: ""
        var nom = readSharedPref("Nom") ?: ""
        var loyaltyNumber = intent.extras?.getString("loyaltyNumber") ?: ""

        tabCarteFragment=TabCarteFragment.newInstance(loyaltyNumber, prenom, nom)

        setContentView(R.layout.activity_tab_bar)
        showProfile()

        val textViewTabCarte = findViewById<TextView>(R.id.textViewTabCarte)
        val textViewTabOffres = findViewById<TextView>(R.id.textViewTabOffres)
        val textViewTabMagasins = findViewById<TextView>(R.id.textViewTabMagasins)

        val userProfileButton = findViewById<ImageView>(R.id.userIcon)

        userProfileButton.setOnClickListener(View.OnClickListener {
            val newIntent =  Intent(application, UserProfileActivity::class.java)
            startActivity(newIntent)
        })


        textViewTabCarte.setOnClickListener(View.OnClickListener {
            showTabCartes()
        })

        textViewTabOffres.setOnClickListener(View.OnClickListener {
            showTabOffres()
        })


        textViewTabMagasins.setOnClickListener(View.OnClickListener {
            showTabMagasins()
        })

        showTabCartes()
    }

    fun showTabCartes(){
        val frManager= supportFragmentManager
        val fragmentTra=frManager.beginTransaction()
        fragmentTra.addToBackStack("TabCarte")
        fragmentTra.replace(R.id.fragmentContent,tabCarteFragment)
        fragmentTra.commit()
    }

    fun showTabOffres(){
        val frManager= supportFragmentManager
        val fragmentTra=frManager.beginTransaction()
        fragmentTra.addToBackStack("TabOffres")
        fragmentTra.replace(R.id.fragmentContent,tabOffresFragment)
        fragmentTra.commit()
    }

    fun showTabMagasins(){
        val frManager= supportFragmentManager
        val fragmentTra=frManager.beginTransaction()
        fragmentTra.addToBackStack("TabMagasins")
        fragmentTra.replace(R.id.fragmentContent,tabMagasinsFragment)
        fragmentTra.commit()
    }
    fun readSharedPref(key:String):String{
        val sharedPreferences: SharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key,"").toString()
    }
}