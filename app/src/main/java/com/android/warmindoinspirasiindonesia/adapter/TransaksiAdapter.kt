package com.android.warmindoinspirasiindonesia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.warmindoinspirasiindonesia.databinding.ListTransaksiBinding
import com.android.warmindoinspirasiindonesia.ui.transaksi.Transaksi
import androidx.navigation.findNavController
import com.android.warmindoinspirasiindonesia.ui.transaksi.TransaksiFragmentDirections
import kotlin.math.min

class TransaksiAdapter(val context: Context, val listTransaksi: ArrayList<Transaksi>) :
    RecyclerView.Adapter<TransaksiAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListTransaksiBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListTransaksiBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Set values to the views
        val currentTransaksi = listTransaksi[position]
        holder.binding.meja.text = currentTransaksi.getNomerMeja()
        holder.binding.jam.text = currentTransaksi.getWaktu()
        holder.binding.status.text = currentTransaksi.getStatusBayar()

        holder.binding.card.setOnClickListener {
            // Navigate to DetailFragment when the card is clicked
            val action = TransaksiFragmentDirections.actionNavTransaksiToNavDetail()
            holder.itemView.findNavController().navigate(action)
        }

        if (position == 0) {
            val marginParams = holder.binding.card.layoutParams as ViewGroup.MarginLayoutParams
            marginParams.topMargin = 130
            holder.binding.card.layoutParams = marginParams
        } else {
            val marginParams = holder.binding.card.layoutParams as ViewGroup.MarginLayoutParams
            marginParams.topMargin = 120
            holder.binding.card.layoutParams = marginParams
        }
    }

    override fun getItemCount(): Int {
        return listTransaksi.size
    }
}
