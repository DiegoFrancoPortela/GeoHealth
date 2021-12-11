package com.geohealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mButtonLogout: Button = findViewById(R.id.btnLogout)

        //Firebase
        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        //

        fun logout() {
            // Metodo para cerrar sesión y cambiar de actividad.
            mAuth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // Finish para terminar esta Activity
            finish()
        }

        mButtonLogout.setOnClickListener() {
            logout()
        }

    }
}