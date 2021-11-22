package com.geohealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

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

    private fun goToSelectAuth() {
        val intent = Intent(this, SelectOptionAuthActivity::class.java)
        startActivity(intent)
    }
}