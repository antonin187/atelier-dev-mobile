package com.antonin.atelierdveloppementmobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "1234"
private const val ARG_PARAM2 = "param2"
private const val ARG_LOYALTY_NUMBER = "456"

/**
 * A simple [Fragment] subclass.
 * Use the [TabCarteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabCarteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_LOYALTY_NUMBER)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_carte, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*Show barcode*/
        val barcodeIV = view.findViewById<ImageView>(R.id.barcodeIV)
        val barcodeTV = view.findViewById<TextView>(R.id.barcodeTV)

        val barEncoder = BarcodeEncoder()
        val bitmap = barEncoder.encodeBitmap(param1, BarcodeFormat.CODABAR, 900, 300)
        barcodeIV.setImageBitmap(bitmap)

        barcodeTV.text = param1
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TabCarteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TabCarteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_LOYALTY_NUMBER, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }
}