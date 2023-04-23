package com.antonin.atelierdveloppementmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ShopDetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_details)

        showBack()

        val shopPicture = findViewById<ImageView>(R.id.shopPicture)
        val shopAddress = findViewById<TextView>(R.id.shopAddress)
        val shopCityZipcode = findViewById<TextView>(R.id.shopCityZipcode)
        val shopDescription = findViewById<TextView>(R.id.shopDescription)


        val nameShop=intent.extras!!.getString("nameShop")

        if (nameShop != null) {
            setHeaderText(nameShop)
        } else {
            setHeaderText("Informations du magasin")
        }

        val imageShop=intent.extras!!.getString("imageShop")
        val address=intent.extras!!.getString("address")
        val zipcode=intent.extras!!.getString("zipcode")
        val city=intent.extras!!.getString("city")
        val description=intent.extras!!.getString("description")


        Picasso.get().load(imageShop).into(shopPicture)
        shopAddress.text = address
        shopCityZipcode.text = city.plus(" - ").plus(zipcode)
        shopDescription.text = description
    }
}