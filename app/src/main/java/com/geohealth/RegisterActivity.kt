package com.geohealth

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val mButtonRegistro: Button = findViewById(R.id.btnRegistro)
        val mTextInputName: TextInputEditText = findViewById(R.id.textInputName)
        val mTextInputEmail: TextInputEditText = findViewById(R.id.textInputEmail)
        val mTextInputPassword: TextInputEditText = findViewById(R.id.textInputPassword)

        // Firebase
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
        // --------


        mButtonRegistro.setOnClickListener() {
            registerUser()
        }

    }
}