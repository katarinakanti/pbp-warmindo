package com.android.warmindoinspirasiindonesia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DetailActivity : AppCompatActivity() {

    private lateinit var btnUbah: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_detail)

        btnUbah = findViewById(R.id.btn_ubah)

        btnUbah.setOnClickListener {
            val intent = Intent(this, EditorActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}