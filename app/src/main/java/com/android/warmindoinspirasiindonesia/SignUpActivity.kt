package com.android.warmindoinspirasiindonesia

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.warmindoinspirasiindonesia.databinding.RegristerActivityBinding

class SignUpActivity: AppCompatActivity() {

    private lateinit var binding: RegristerActivityBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = RegristerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.btnRegrister.setOnClickListener{
            val signupUsername = binding.editTextTextUsername.text.toString()
            val signupEmail = binding.editTextTextEmailAddress.text.toString()
            val signupPassword = binding.editTextTextPassword.text.toString()

            signupDatabase(signupUsername, signupEmail, signupPassword)
        }

        binding.LoginRedirect. setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signupDatabase(username: String, email: String, password: String){
        val insertedRowId = databaseHelper.insertUser(username, email, password)
        if (insertedRowId != -1L){
            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        else{
            Toast.makeText(this,"Signup Failed", Toast.LENGTH_SHORT).show()
        }
    }
}