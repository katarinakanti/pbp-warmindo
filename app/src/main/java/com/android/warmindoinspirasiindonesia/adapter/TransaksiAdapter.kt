package com.android.warmindoinspirasiindonesia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.warmindoinspirasiindonesia.databinding.ListTransaksiBinding
import com.android.warmindoinspirasiindonesia.ui.transaksi.Transaksi
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
        holder.binding.status2.text = currentTransaksi.getStatusBayar2()
    }

    override fun getItemCount(): Int {
        return listTransaksi.size
    }
}
