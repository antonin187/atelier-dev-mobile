package com.antonin.atelierdveloppementmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import com.journeyapps.barcodescanner.BarcodeEncoder


class UserProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        showBack()

        val loyaltyNumber = intent.extras!!.getString("loyaltyNumber")
        val barcodeIV = findViewById<ImageView>(R.id.barcodeIV)
        val barcodeTV = findViewById<TextView>(R.id.barcodeTV)

        val barEncoder = BarcodeEncoder()
        val bitmap = barEncoder.encodeBitmap(loyaltyNumber, BarcodeFormat.CODABAR, 900, 300)
        barcodeIV.setImageBitmap(bitmap)

        barcodeTV.text = loyaltyNumber
    }
}