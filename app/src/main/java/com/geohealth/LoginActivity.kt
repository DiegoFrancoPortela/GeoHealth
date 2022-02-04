package com.geohealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setTitle("Login")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mTextInputEmail: TextInputEditText = findViewById(R.id.textInputEmail)
        val mTextInputPassword: TextInputEditText = findViewById(R.id.textInputPassword)

        // Firebase
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
        // --------

        val mButtonLogin: Button = findViewById(R.id.btnLogin)

         fun login() {
            var email: String = mTextInputEmail.getText().toString()
            var password: String = mTextInputPassword.getText().toString()

            if (!email.isEmpty() && !password.isEmpty()) {
                // Validación para ver si la contraseña en mayor o igual a 6
                if (password.length >= 6) {
                    mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful()) {
                                //Toast.makeText(this,"Login Exitoso",Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, MapActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                            } else {
                                Toast.makeText(this,"Email o Contraseña incorrectos",Toast.LENGTH_SHORT).show()
                            }
                        }
                }
                else {
                    Toast.makeText(this,"La contraseña debe tener más de 6 carácteres",Toast.LENGTH_SHORT).show()
                }
            }
             else {
                Toast.makeText(this,"La contraseña y el Email son obligatorios",Toast.LENGTH_SHORT).show()
            }
        }

        mButtonLogin.setOnClickListener() {
            login()
        }

    }
}