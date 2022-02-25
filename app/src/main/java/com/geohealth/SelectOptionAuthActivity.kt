package com.geohealth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SelectOptionAuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_option_auth)
        setSupportActionBar(findViewById(R.id.toolbar))
        val valor = intent.getStringExtra("tipo")
        supportActionBar?.setTitle("Seleccionar Opcion")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val mButtonTengoCuenta: Button = findViewById(R.id.botonGoToLogin)
        val mButtonRegistro: Button = findViewById(R.id.botonGoToRegister)

        mButtonTengoCuenta.setOnClickListener() {
            goToLogin()
        }

        mButtonRegistro.setOnClickListener() {
            if (valor != null) {
                goToRegister(valor)
            }
        }
    }

    private fun goToRegister(valor: String) {
        if (valor == "cliente"){
            val intent = Intent(this, RegisterActivity::class.java)
            println("llendo registro cliente")
            startActivity(intent)
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}