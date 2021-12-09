package com.geohealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toolbar

class SelectOptionAuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_option_auth)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setTitle("Seleccionar Opcion")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val mButtonTengoCuenta: Button = findViewById(R.id.botonGoToLogin)
        val mButtonRegistro: Button = findViewById(R.id.botonGoToRegister)

        mButtonTengoCuenta.setOnClickListener() {
            goToLogin()
        }

        mButtonRegistro.setOnClickListener() {
            goToRegister()
        }
    }

    private fun goToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}