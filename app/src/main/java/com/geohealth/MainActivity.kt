package com.geohealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mButtonMedic: Button = findViewById(R.id.btnMedic)
        val mButtonPacient: Button = findViewById(R.id.btnClient)

        fun goToSelectAuth() {
            val intent = Intent(this, SelectOptionAuthActivity::class.java)
            startActivity(intent)
        }
        mButtonPacient.setOnClickListener() {
            goToSelectAuth()
        }
        mButtonMedic.setOnClickListener() {
            goToSelectAuth()
        }


    }
}