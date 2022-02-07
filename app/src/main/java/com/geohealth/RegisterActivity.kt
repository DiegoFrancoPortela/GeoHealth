package com.geohealth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geohealth.databinding.ActivityRegisterBinding
import com.geohealth.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.setTitle("Registrar Usuario")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Firebase
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
        // --------

        fun guardarUsuario(id: String, name:String, email:String) {

            val user = User()

            user.name = name
            user.email = email

            mDatabase.child("usuarios").child("clientes").child(id).setValue(user).addOnCompleteListener()
            { task ->
                if (task.isSuccessful()) {
                    Toast.makeText(this,"Registro Completado",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this,"Registro Fallido",Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun registerUser() {
            var name: String = binding.textInputName.getText().toString()
            var email: String = binding.textInputEmail.getText().toString()
            var password: String = binding.textInputPassword.getText().toString()

            if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                if (password.length >= 6) {
                    mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful()) {
                                var id = mAuth.currentUser?.uid;
                                guardarUsuario(id.toString(), name, email)
                                //Toast.makeText(this,"Registro Correcto",Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, MapActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                            } else {
                                Toast.makeText(this,"No se pudo registrar el usuario",Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(this,"La contraseña debe tener al menos 6 carácteres",Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this,"Debe completar todos los campos",Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRegistro.setOnClickListener() {
            registerUser()
        }

    }

}