package com.geohealth

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.geohealth.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    // SharedPreferences nos servirá para guardar datos tipo clave-valor
    /*
    Con esto podremos almacenar datos tipo "usuario-paciente" o "usuario-enfermero" y poder
    distinguirlos. Además, los datos de SharedPreferences no se eliminarán a menos que desintalemos la
    aplicación o se lo pidamos nosotros.
    */

    private lateinit var mPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        mPref = applicationContext.getSharedPreferences("typeUser", MODE_PRIVATE)
        var editor : SharedPreferences.Editor = mPref.edit()

        setContentView(binding.root)

        binding.btnSoyPaciente.setOnClickListener() {
            editor.putString("usuario","paciente")
            editor.apply()
            goToSelectAuth()
        }

        binding.btnSoyEnfermero.setOnClickListener() {
            editor.putString("usuario","enfermero")
            editor.apply()
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