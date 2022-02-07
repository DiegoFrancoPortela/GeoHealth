package com.geohealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geohealth.databinding.ActivitySelectOptionAuthBinding

class SelectOptionAuthActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySelectOptionAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectOptionAuthBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.setTitle("Seleccionar Opción")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnTengoCuenta.setOnClickListener() {
            goToLogin()
        }

        binding.btnRegistro.setOnClickListener() {
            goToRegister()
        }

    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun goToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}