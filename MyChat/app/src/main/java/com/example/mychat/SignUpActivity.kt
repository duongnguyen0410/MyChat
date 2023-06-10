package com.example.mychat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.mychat.databinding.ActivitySignUpBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString()
            Log.d("SIGNUP", email)
            val pass = binding.etPassword.text.toString()
            Log.d("SIGNUP", pass)
            val confirmPass = binding.etConfirmPassword.text.toString()

            signUpWithEmailAndPassword(email, pass, confirmPass)
        }
    }

    private fun signUpWithEmailAndPassword(email: String, pass: String, confirmPass: String){
        if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
            if (pass == confirmPass) {
                Log.d("SINGUP", "same pass")
                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Sign up success", Toast.LENGTH_SHORT).show()
                        signInWithEmailAndPassword(email, pass)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        Log.d("SIGNUP", it.exception.toString())
                        Toast.makeText(this, "Not success", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Password not match", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Empty fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login success
                    val user: FirebaseUser? = auth.currentUser
                    Toast.makeText(this, "loginsuccess", Toast.LENGTH_SHORT).show()
                    val intent: Intent = Intent(this, CreateProfileActivity::class.java)
                    startActivity(intent)
                } else {
                    // Login Failed
                    Toast.makeText(this, "Login failed. ", Toast.LENGTH_SHORT).show()
                    Log.d("Login failed", "signInWithEmailAndPassword: " + task.exception.toString())
                }
            }
    }
}

