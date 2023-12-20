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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_detail)

        btnUbah = findViewById(R.id.btn_ubah)
        tvResult = findViewById(R.id.tv_result)

        val receivedStatus = intent.getStringExtra("STATUS_PESANAN")
        Log.d("DetailActivity", "Status Pesanan: $receivedStatus")
        tvResult.text = receivedStatus

        btnUbah.setOnClickListener {
            val intent = Intent(this, EditorActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}