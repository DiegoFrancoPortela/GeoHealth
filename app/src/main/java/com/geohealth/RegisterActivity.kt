package com.geohealth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.geohealth.modelos.Usuarios
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference()

        val mButtonRegister: Button = findViewById(R.id.btnRegister)
        val mTextInputEmail: TextInputEditText = findViewById(R.id.textInputEmail)
        val mTextInputPassword: TextInputEditText = findViewById(R.id.textInputPassword)
        val mTextInputName: TextInputEditText = findViewById(R.id.textInputName)
        fun saveUser(name: String,email: String) {
            val user = Usuarios();

            user.name = name
            user.email = email

            mDatabase.child("usuarios").child("clientes").push().setValue(user).addOnCompleteListener() {
                task ->
                if(task.isSuccessful()){
                    Toast.makeText(this,"Registro Completado",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this,"Registro Fallido",Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun registerUser() {
            var name: String = mTextInputName.getText().toString()
            var email: String = mTextInputEmail.getText().toString()
            var password: String = mTextInputPassword.getText().toString()

            if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

                if (password.length >= 6){

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
                        if (task.isSuccessful()) {
                            saveUser(name,email)
                            Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this,"No se pudo registrar el usuario",Toast.LENGTH_SHORT).show()
                        }
                    }

                }else{
                    Toast.makeText(this,"La contraseña debe tener más de 6 carácteres",Toast.LENGTH_SHORT).show()
                }

            }else {

                Toast.makeText(this,"Ingrese todos los campos",Toast.LENGTH_SHORT).show()
            }

        }

        mButtonRegister.setOnClickListener(){
            registerUser()
        }


    }


}

private fun DatabaseReference.setValue() {
    TODO("Not yet implemented")
}







































