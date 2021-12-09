package com.example.hola.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hola.R
import com.example.hola.model.User
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var bodegaList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return bodegaList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = bodegaList[position]
        holder.itemView.ruc_txt.text = currentItem.ruc
        holder.itemView.nombre_txt.text = currentItem.nombre
        holder.itemView.direccion_txt.text = currentItem.direccion
        holder.itemView.region_txt.text = currentItem.region
        holder.itemView.provincia_txt.text = currentItem.provincia
        holder.itemView.distrito_txt.text = currentItem.distrito
        holder.itemView.producto_txt.text = currentItem.producto

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<User>){
        this.bodegaList = user
        notifyDataSetChanged()
    }
}