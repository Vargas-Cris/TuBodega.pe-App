package com.example.hola.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hola.R
import com.example.hola.model.User
import com.example.hola.viewmodel.BodegaViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mBodegaViewModel: BodegaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mBodegaViewModel = ViewModelProvider(this).get(BodegaViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val ruc= addRuc_et.text.toString()
        val nombre = addNombre_et.text.toString()
        val direccion = addDireccion_et.text.toString()
        val region=addRegion_et.text.toString()
        val provincia=addProvincia_et.text.toString()
        val distrito=addDistrito_et.text.toString()
        val producto=addProducto_et.text.toString()


        if(inputCheck(ruc,nombre,direccion,region,provincia,distrito,producto)){
            // Create User Object
            val bodega = User(
                0,
                ruc,
                nombre,
                direccion,
                region,
                provincia,
                distrito,
                producto
            )
            // Add Data to Database
            mBodegaViewModel.addBodega(bodega)
            Toast.makeText(requireContext(), "Creado exitosamente!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Completa los campos", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(ruc: String, nombre: String, direccion: String,region:String,provincia:String,distrito:String,producto:String): Boolean{
        return !(TextUtils.isEmpty(ruc) && TextUtils.isEmpty(nombre) && TextUtils.isEmpty(direccion) && TextUtils.isEmpty(region) && TextUtils.isEmpty(provincia) && TextUtils.isEmpty(distrito) && TextUtils.isEmpty(producto))
    }

}