package com.antonin.atelierdveloppementmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

open class BaseActivity : AppCompatActivity() {

    fun setHeaderTitle(title: String?) {
        val textView = findViewById<TextView>(R.id.textViewTitle)
        textView.setText(title)
    }

    fun showBack() {
        val imageViewBack = findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.visibility = View.VISIBLE
        imageViewBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }
}