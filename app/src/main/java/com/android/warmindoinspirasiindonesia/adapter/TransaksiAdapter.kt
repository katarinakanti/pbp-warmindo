package com.android.warmindoinspirasiindonesia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.warmindoinspirasiindonesia.databinding.ListShiftBinding
import com.android.warmindoinspirasiindonesia.databinding.ListTransaksiBinding
import com.android.warmindoinspirasiindonesia.ui.home.Shift
import com.android.warmindoinspirasiindonesia.ui.transaksi.Transaksi

class TransaksiAdapter(val context: Context, val listTransaksi: ArrayList<Transaksi>, private val onItemClick: (Transaksi) -> Unit) :
    RecyclerView.Adapter<TransaksiAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListTransaksiBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListTransaksiBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTransaksi = listTransaksi[position]
        holder.binding.meja.text = currentTransaksi.getNomerMeja()
        holder.binding.jam.text = currentTransaksi.getWaktu()
        holder.binding.status.text = currentTransaksi.getStatus()

        // Add margin to the card
        if (position == 0) {
            val marginParams = holder.binding.card.layoutParams as ViewGroup.MarginLayoutParams
            marginParams.topMargin = 150
            marginParams.leftMargin = 90
            marginParams.rightMargin = 90
            holder.binding.card.layoutParams = marginParams
        } else {
            val marginParams = holder.binding.card.layoutParams as ViewGroup.MarginLayoutParams
            marginParams.topMargin = 50
            marginParams.leftMargin = 90
            marginParams.rightMargin = 90
            holder.binding.card.layoutParams = marginParams
        }

        holder.itemView.setOnClickListener {
            onItemClick(currentTransaksi) // Trigger the onItemClick callback
        }
    }

    override fun getItemCount(): Int {
        return listTransaksi.size
    }
}

