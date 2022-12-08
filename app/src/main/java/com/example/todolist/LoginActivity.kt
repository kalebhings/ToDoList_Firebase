package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var signIn : Button;
    private lateinit var auth: FirebaseAuth
    private lateinit var emailInput : EditText
    private lateinit var passInput : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signIn = findViewById(R.id.signIn);
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        val registerButton = findViewById<Button>(R.id.register)
        emailInput = findViewById(R.id.email)
        passInput = findViewById(R.id.password)

        signIn.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passInput.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val login = Intent(this, MainActivity::class.java)
                        startActivity(login)
                    }
                    else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else {
                Toast.makeText(this, "Fields can't be empty", Toast.LENGTH_SHORT).show()
            }
        }

        registerButton.setOnClickListener {
            val register = Intent(this, RegisterActivity::class.java)
            startActivity(register)
            finish()
        }
    }
}