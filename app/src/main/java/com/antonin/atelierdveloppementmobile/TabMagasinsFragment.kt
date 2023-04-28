package com.antonin.atelierdveloppementmobile

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TabMagasinsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabMagasinsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var googleMap: GoogleMap

    @SuppressLint("MissingPermission")
    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                googleMap.isMyLocationEnabled = true
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                googleMap.isMyLocationEnabled = true
            }
            else -> {
                // No location access granted.
            }
        }
    }

    val shops = arrayListOf<Shop>()

    val callback = OnMapReadyCallback { googleMap ->

        for (shop in shops) {
            val marker = MarkerOptions()
            val shopLatLng = LatLng(shop.latitude, shop.longitude)
            marker.title(shop.name)
            marker.position(shopLatLng)
            googleMap.addMarker(marker)
        }

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(48.854885, 2.338646), 5f))

        googleMap.setOnInfoWindowClickListener {


            for (shop in shops) {
                if(shop.latitude == it.position.latitude && shop.longitude == it.position.longitude) {
                    val newIntent = Intent(activity, ShopDetailsActivity::class.java)
                    newIntent.putExtra("nameShop", shop.name)
                    newIntent.putExtra("imageShop", shop.pictureStore)
                    newIntent.putExtra("address", shop.address)
                    newIntent.putExtra("zipcode", shop.zipcode)
                    newIntent.putExtra("city", shop.city)
                    newIntent.putExtra("description", shop.description)
                    startActivity(newIntent)
                }
            }

        }

        this.googleMap = googleMap
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()

        val mRequestURL = "https://www.ugarit.online/epsi/stores.json"
        val request = Request.Builder()
            .url(mRequestURL)
            .get()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()

                if(data!=null) {
                    val jsShops = JSONObject(data)
                    val jsArrayShops = jsShops.getJSONArray("stores")
                    for (i in 0 until jsArrayShops.length()) {
                        val js = jsArrayShops.getJSONObject(i)
                        val shop = Shop(
                            js.optInt("storeId", -1),
                            js.optString("name", "not found"),
                            js.optString("description", "not found"),
                            js.optString("pictureStore", "not found"),
                            js.optString("address", "not found"),
                            js.optString("zipcode", "not found"),
                            js.optString("city", "not found"),
                            js.optDouble("longitude", 0.0),
                            js.optDouble("latitude", 0.0)
                        )
                        shops.add(shop)
                    }
                }
            }

        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_magasins, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TabMagasinsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TabMagasinsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}