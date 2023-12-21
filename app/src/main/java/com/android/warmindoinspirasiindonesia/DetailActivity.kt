package com.android.warmindoinspirasiindonesia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.widget.Button
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    private lateinit var btnUbah: Button

    private lateinit var tvResult: TextView
    private lateinit var tvMeja: TextView
    private lateinit var tvShift: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_detail)

        btnUbah = findViewById(R.id.btn_ubah)

        tvResult = findViewById(R.id.tv_result)
        tvMeja = findViewById(R.id.nomer_meja2)
        tvShift = findViewById(R.id.shift)

        val receivedMeja = intent.getStringExtra("MEJA")
        val receivedStatus = intent.getStringExtra("STATUS_BAYAR")
        val receivedShift = intent.getStringExtra("SHIFT")
        tvResult.text = receivedStatus
        tvMeja.text = receivedMeja
        tvShift.text = receivedShift

        btnUbah.setOnClickListener {
            val intent = Intent(this, EditorActivity::class.java)
            intent.putExtra("MEJA", receivedMeja)
            intent.putExtra("STATUS_BAYAR", receivedStatus)
            intent.putExtra("SHIFT", receivedShift)
            startActivity(intent)
            Log.d("DetailActivity", "EditorActivity started")
//            finish()
        }

    }
}