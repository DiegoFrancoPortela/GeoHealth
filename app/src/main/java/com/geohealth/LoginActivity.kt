package com.geohealth

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.geohealth.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.setTitle("Login")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Firebase
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
        // --------

        // Progress Dialog
        val progressDialog = ProgressDialog(this@LoginActivity)
        progressDialog.setTitle("Iniciando Sesión...")
        progressDialog.setMessage("Procesando datos, espere...")
        // --------

        binding.botonAtras.setOnClickListener { view ->
            val intent = Intent(this, SelectOptionAuthActivity::class.java)
            startActivity(intent)
        }

         fun login() {
            var email: String = binding.textInputEmail.getText().toString()
            var password: String = binding.textInputPassword.getText().toString()

            if (!email.isEmpty() && !password.isEmpty()) {
                progressDialog.show()
                // Validación para ver si la contraseña en mayor o igual a 6
                if (password.length >= 6) {
                    mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful()) {
                                //Toast.makeText(this,"Login Exitoso",Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, MainMenuActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                            } else {
                                Toast.makeText(this,"Email o Contraseña incorrectos",Toast.LENGTH_SHORT).show()
                                progressDialog.dismiss()
                            }
                        }
                }
                else {
                    Toast.makeText(this,"La contraseña debe tener más de 6 carácteres",Toast.LENGTH_SHORT).show()
                    progressDialog.dismiss()
                }
            }
             else {
                Toast.makeText(this,"La contraseña y el Email son obligatorios",Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLogin.setOnClickListener() {
            login()
        }

    }
}