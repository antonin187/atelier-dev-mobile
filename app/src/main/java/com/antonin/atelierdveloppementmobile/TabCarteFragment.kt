package com.antonin.atelierdveloppementmobile

import android.os.Bundle
import android.util.Log
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
private const val ARG_LOYALTY_NUMBER = "1234"
private const val ARG_PRENOM = "param2"
private const val ARG_NOM = "456"

/**
 * A simple [Fragment] subclass.
 * Use the [TabCarteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabCarteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var loyaltyNumberParam: String? = null
    private var prenomParam: String? = null
    private var nomParam: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            loyaltyNumberParam = it.getString(ARG_LOYALTY_NUMBER)
            prenomParam = it.getString(ARG_PRENOM)
            nomParam = it.getString(ARG_NOM)
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
        val prenomNomTV = view.findViewById<TextView>(R.id.prenomNom)

        prenomParam?.let { Log.i("PRENOM", it) }
        nomParam?.let { Log.i("NOM", it) }

        prenomNomTV.text = prenomParam.plus(" ").plus(nomParam)

        val barEncoder = BarcodeEncoder()
        val bitmap = barEncoder.encodeBitmap(loyaltyNumberParam, BarcodeFormat.CODABAR, 900, 300)
        barcodeIV.setImageBitmap(bitmap)

        barcodeTV.text = loyaltyNumberParam

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param loyaltyNumber Parameter 1.
         * @param prenom Parameter 2.
         * @param nom Parameter 3.
         * @return A new instance of fragment TabCarteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(loyaltyNumber: String, prenom: String, nom: String) =
            TabCarteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_LOYALTY_NUMBER, loyaltyNumber)
                    putString(ARG_PRENOM, prenom)
                    putString(ARG_NOM, nom)
                }
            }

    }
}