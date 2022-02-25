package com.geohealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        database =
            Firebase.database("https://geohealthalex-8d6b2-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("Users")

        val mButtonMedic: Button = findViewById(R.id.btnMedic)
        val mButtonPacient: Button = findViewById(R.id.btnClient)
        fun goToLogin(tipo: String) {
            val intent = Intent(this, LoginActivity::class.java)

            intent.putExtra("tipo", tipo)

            startActivity(intent)
        }
        mButtonPacient.setOnClickListener() {
            goToLogin("cliente")
        }
        mButtonMedic.setOnClickListener() {
            goToLogin("profesional")
        }


    }
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

}