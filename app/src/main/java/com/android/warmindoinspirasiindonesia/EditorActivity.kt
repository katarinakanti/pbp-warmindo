package com.android.warmindoinspirasiindonesia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class EditorActivity : AppCompatActivity() {

    private lateinit var statusPesanan: Spinner
    private lateinit var btnSimpan: Button
    private lateinit var tvMeja: TextView
    private lateinit var tvShift: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        statusPesanan = findViewById(R.id.list_status)
        btnSimpan = findViewById(R.id.btn_simpan)

        tvMeja = findViewById(R.id.nomer_meja2)
        tvShift = findViewById(R.id.shift)

        val receivedMeja = intent.getStringExtra("MEJA")
        val receivedShift = intent.getStringExtra("SHIFT")
        tvMeja.text = receivedMeja
        tvShift.text = receivedShift

        btnSimpan.setOnClickListener {
            val toast = Toast.makeText(this, "Status Pesanan ${statusPesanan.selectedItem}",
                Toast.LENGTH_SHORT)
            toast.show()
            val selectedStatus = statusPesanan.selectedItem.toString()

            // Menggunakan Intent1 untuk DetailActivity
            val intent1 = Intent(this, DetailActivity::class.java)
            intent1.putExtra("STATUS_BAYAR", selectedStatus)
            intent1.putExtra("MEJA", receivedMeja)
            intent1.putExtra("SHIFT", receivedShift)
            startActivity(intent1)

            // Menggunakan Intent2 untuk TransaksiActivity
            val intent2 = Intent(this, TransaksiActivity::class.java)
            intent2.putExtra("STATUS_BAYAR", selectedStatus)
            intent2.putExtra("MEJA", receivedMeja)
            intent2.putExtra("SHIFT", receivedShift)
            startActivity(intent2)
        }
    }
}