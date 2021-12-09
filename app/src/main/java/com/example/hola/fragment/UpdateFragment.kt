package com.example.hola.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hola.R
import com.example.hola.model.User
import com.example.hola.viewmodel.BodegaViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {


        private val args by navArgs<UpdateFragmentArgs>()

        private lateinit var mBodegaViewModel: BodegaViewModel

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            val view = inflater.inflate(R.layout.fragment_update, container, false)

            mBodegaViewModel = ViewModelProvider(this).get(BodegaViewModel::class.java)

            view.updateRuc_et.setText(args.currentUser.ruc)
            view.updateNombre_et.setText(args.currentUser.nombre)
            view.updateDireccion_et.setText(args.currentUser.direccion)
            view.updateRegion_et.setText(args.currentUser.region)
            view.updateProvincia_et.setText(args.currentUser.provincia)
            view.updateDistrito_et.setText(args.currentUser.distrito)
            view.updateProducto_et.setText(args.currentUser.producto)


            view.update_btn.setOnClickListener {
                updateItem()
            }

            // Add menu
            setHasOptionsMenu(true)

            return view
        }

        private fun updateItem() {
            val ruc = updateRuc_et.text.toString()
            val nombre = updateNombre_et.text.toString()
            val direccion = updateDireccion_et.text.toString()
            val region = updateRegion_et.text.toString()
            val provincia= updateProvincia_et.text.toString()
            val distrito = updateDistrito_et.text.toString()
            val producto = updateProducto_et.text.toString()


            if (inputCheck(ruc, nombre, direccion,region,provincia,distrito,producto)) {
                // Create User Object
                val updatedBodega = User(args.currentUser.id,ruc,nombre,direccion,region,provincia,distrito,producto)
                // Update Current User
                mBodegaViewModel.updateBodega(updatedBodega)
                Toast.makeText(requireContext(), "Actualizado correctamente!", Toast.LENGTH_SHORT).show()
                // Navigate Back
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            } else {
                Toast.makeText(requireContext(), "Llena los campos", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        private fun inputCheck(ruc: String, nombre: String, direccion: String,region:String,provincia:String,distrito:String,producto:String): Boolean{
            return !(TextUtils.isEmpty(ruc) && TextUtils.isEmpty(nombre) && TextUtils.isEmpty(direccion) && TextUtils.isEmpty(region) && TextUtils.isEmpty(provincia) && TextUtils.isEmpty(distrito) && TextUtils.isEmpty(producto))
        }

        override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            inflater.inflate(R.menu.delete_menu, menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (item.itemId == R.id.menu_delete) {
                deleteBodega()
            }
            return super.onOptionsItemSelected(item)
        }

        private fun deleteBodega() {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Si") { _, _ ->
                mBodegaViewModel.deleteBodega(args.currentUser)
                Toast.makeText(
                    requireContext(),
                    "Removido exitosamente: ${args.currentUser.ruc}",
                    Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Eliminar ${args.currentUser.ruc}?")
            builder.setMessage("Seguro de eliminar la tienda  ${args.currentUser.ruc}?")
            builder.create().show()
        }
    }