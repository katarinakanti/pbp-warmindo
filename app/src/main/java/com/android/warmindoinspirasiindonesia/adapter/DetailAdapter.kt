package com.android.warmindoinspirasiindonesia.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.warmindoinspirasiindonesia.EditorActivity
import com.android.warmindoinspirasiindonesia.R
import com.android.warmindoinspirasiindonesia.ui.detail.Detail

abstract class DetailAdapter(private var status: List<Detail>) :
    RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    class DetailViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val statusTextView: TextView = itemView.findViewById(R.id.tv_result)
            val updateButton: Button = itemView.findViewById(R.id.btn_simpan)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_detail, parent)
        return DetailViewHolder(view)
    }


    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val status = status[position]
        holder.statusTextView.text = status.status

        holder.updateButton.setOnClickListener{
            val intent = Intent(holder.itemView.context, EditorActivity::class.java).apply{
                putExtra("status", status.status)
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    fun refreshData(newStatus: List<Detail>){
        status = newStatus
        notifyDataSetChanged()
    }
}