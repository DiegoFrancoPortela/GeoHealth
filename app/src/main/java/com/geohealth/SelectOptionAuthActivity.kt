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

        supportActionBar?.setTitle("Seleccionar Opción")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mButtonTengoCuenta: Button = findViewById(R.id.btnTengoCuenta)
        val mButtonRegistro: Button = findViewById(R.id.btnRegistro)

        mButtonTengoCuenta.setOnClickListener() {
            goToLogin()
        }

        mButtonRegistro.setOnClickListener() {

        }

    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}