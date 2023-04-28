package com.antonin.atelierdveloppementmobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import org.json.JSONObject

class CreateAccountActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val buttonQRCode = findViewById<ImageButton>(R.id.qrCodeButton)
        val buttonForm = findViewById<Button>(R.id.formButton)

        buttonQRCode.setOnClickListener {
            val integrator = IntentIntegrator(this)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            integrator.setPrompt("Scan a QR code")
            integrator.setCameraId(0)
            integrator.setBeepEnabled(false)
            integrator.setBarcodeImageEnabled(false)
            integrator.initiateScan()
        }
        buttonForm.setOnClickListener(View.OnClickListener {
            val newIntent =  Intent(application, FormInscriptionActivity::class.java)
            startActivity(newIntent)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                // Le contenu du QR code est stocké dans result.contents
                val objJson = JSONObject(result.contents)
                val newIntent = Intent(application, FormInscriptionActivity::class.java)
                newIntent.putExtra("firstName", objJson.getString("firstName"))
                newIntent.putExtra("lastName", objJson.getString("lastName"))
                newIntent.putExtra("email", objJson.getString("email"))
                newIntent.putExtra("address", objJson.getString("address"))
                newIntent.putExtra("zipcode", objJson.getString("zipcode"))
                newIntent.putExtra("city", objJson.getString("city"))
                newIntent.putExtra("cardRef", objJson.getString("cardRef"))
                newIntent.putExtra("qrCode", true)
                startActivity(newIntent)
            } else {
                Toast.makeText(this, "Scan cancelled", Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
