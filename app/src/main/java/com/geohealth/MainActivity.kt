package com.geohealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mButtonSoyPaciente: Button = findViewById(R.id.btnSoyPaciente)
        val mButtonSoyEnfermero: Button = findViewById(R.id.btnSoyEnfermero)

        mButtonSoyPaciente.setOnClickListener() {
            goToSelectAuth()
        }

        mButtonSoyEnfermero.setOnClickListener() {
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