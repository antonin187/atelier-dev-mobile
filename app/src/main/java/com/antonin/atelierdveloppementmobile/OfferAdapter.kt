package com.antonin.atelierdveloppementmobile

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class OfferAdapter(val offers: ArrayList<Offer>): RecyclerView.Adapter<OfferAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imageViewPictureOffer = view.findViewById<ImageView>(R.id.imageViewPictureOffer)
        val textViewNameOffer = view.findViewById<TextView>(R.id.textViewNameOffer)
        val textViewDescriptionOffer = view.findViewById<TextView>(R.id.textViewDescriptionOffer)
        val layoutOffer= view.findViewById<LinearLayout>(R.id.layoutOffer)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.offer_button, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val offer = offers.get(position)
        holder.textViewNameOffer.text=offer.name
        holder.textViewDescriptionOffer.text=offer.description

        val imageView = holder.imageViewPictureOffer
        Picasso.get().load(offer.picture_url).into(imageView);

        /*holder.layoutOffer.setOnClickListener(View.OnClickListener {
            val newIntent = Intent(holder.layoutOffer.context, ProductActivity::class.java)
            newIntent.putExtra("name", product.name)
            newIntent.putExtra("description", product.description)
            newIntent.putExtra("picture_url", product.picture_url)
            holder.layoutProduct.context.startActivity(newIntent)
        })*/
    }

    override fun getItemCount(): Int {
        return offers.size
    }
}