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
            // Gets the values after the button is clicked
            val email = emailInput.text.toString()
            val password = passInput.text.toString()

            // If the fields aren't empty, then move on
            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    // If signing in is successful, send to MainActivity
                    if (it.isSuccessful) {
                        val login = Intent(this, MainActivity::class.java)
                        startActivity(login)
                    }
                    else {
                        // If not successful in signing in, give an exception through a Toast message to the user
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else {
                Toast.makeText(this, "Fields can't be empty", Toast.LENGTH_SHORT).show()
            }
        }

        // Take user to the Registration activity on button press
        registerButton.setOnClickListener {
            val register = Intent(this, RegisterActivity::class.java)
            startActivity(register)
            finish()
        }
    }
}