package com.antonin.atelierdveloppementmobile

import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView

open class BaseActivity : AppCompatActivity() {

    fun showBack() {
        val imageViewBack = findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.visibility = View.VISIBLE
        imageViewBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    fun showProfile() {
        val imageUserIcon = findViewById<ImageView>(R.id.userIcon)
        imageUserIcon.visibility = View.VISIBLE
        imageUserIcon.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    fun setHeaderText(text: String) {
        val textHeader = findViewById<TextView>(R.id.textHeader)
        textHeader.text = text
    }
}