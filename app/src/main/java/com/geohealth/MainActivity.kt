package com.geohealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.geohealth.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnSoyPaciente.setOnClickListener() {
            goToSelectAuth()
        }

        binding.btnSoyEnfermero.setOnClickListener() {
            goToSelectAuth()
        }

    }

    @Override
    override fun onStart() {
        super.onStart()

        // Si esto se cumple, quiere decir que tenemos un usuario creado.
        // Básicamente, al tener una sesion ya abierta, directamente evita el Login y el Register.
        if (FirebaseAuth.getInstance().currentUser != null) {
            val intent = Intent(this, MapActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private fun goToSelectAuth() {
        val intent = Intent(this, SelectOptionAuthActivity::class.java)
        startActivity(intent)
    }
}