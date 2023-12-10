package com.android.warmindoinspirasiindonesia

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

        private lateinit var emailAddressEditText: EditText
        private lateinit var passwordEditText: EditText
        private lateinit var loginButton: Button

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.login_activity)

            // Inisialisasi View
            emailAddressEditText = findViewById(R.id.editTextTextEmailAddress)
            passwordEditText = findViewById(R.id.editTextTextPassword)
            loginButton = findViewById(R.id.btnLogin)

            // Set onClickListener untuk tombol login
            loginButton.setOnClickListener {
                val emailAddress = emailAddressEditText.text.toString()
                val password = passwordEditText.text.toString()
            }
        }
}