package com.geohealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private val TAG = "RealTime"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        database = Firebase.database("https://geohealthalex-8d6b2-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")

        val mButtonMedic: Button = findViewById(R.id.btnMedic)
        val mButtonPacient: Button = findViewById(R.id.btnClient)
        val mButtonFirebase: Button = findViewById(R.id.firebase)

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
        mButtonFirebase.setOnClickListener() {
            writeNewData("Alex",2.1152,-5.1446)
        }


    }
    fun writeNewData(nombre: String, latitud: Double, longitud: Double){
        val profesional = firebase(nombre,latitud,longitud)
        database.child("AA01").setValue(profesional)
    }
}