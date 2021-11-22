package com.geohealth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    val mTextInputEmail: TextInputEditText = findViewById(R.id.textInputEmail)
    val mTextInputPassword: TextInputEditText = findViewById(R.id.textInputPassword)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val mButtonLogin: Button = findViewById(R.id.btnLogin)

        mButtonLogin.setOnClickListener() {
            login()
        }

    }

    private fun login() {
        var email: String = mTextInputEmail.getText().toString()
        var password: String = mTextInputPassword.getText().toString()

        if (!email.isEmpty() && !password.isEmpty()) {
            // Validación para ver si la contraseña en mayor o igual a 6
            if (password.length >= 6) {

            }
        }
    }


}