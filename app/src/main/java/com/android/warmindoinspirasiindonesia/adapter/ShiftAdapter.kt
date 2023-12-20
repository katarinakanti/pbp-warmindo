package com.android.warmindoinspirasiindonesia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.warmindoinspirasiindonesia.databinding.ListShiftBinding
import com.android.warmindoinspirasiindonesia.ui.home.Shift

class ShiftAdapter(val context: Context, val listShift: ArrayList<Shift>, private val onItemClick: (Shift) -> Unit) :
    RecyclerView.Adapter<ShiftAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListShiftBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListShiftBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentShift = listShift[position]
        holder.binding.title.text = currentShift.getTitle()
        holder.binding.time.text = currentShift.getTime()

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
            onItemClick(currentShift) // Trigger the onItemClick callback
        }
    }

    override fun getItemCount(): Int {
        return listShift.size
    }
}