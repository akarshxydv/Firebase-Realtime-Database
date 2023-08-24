package com.example.loginsignupfrd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginsignupfrd.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=FirebaseAuth.getInstance()
        binding.loginbtn.setOnClickListener(){
            var email=binding.Email.text.toString()
            var password=binding.Password.text.toString()
            login(email,password)
        }
        binding.signup.setOnClickListener(){
            startActivity(Intent(this,SignupA::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        var email=binding.Email.text.trim().toString()
        val user: FirebaseUser? = auth.currentUser
        user?.let {
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(this,"Welcome $email",Toast.LENGTH_SHORT).show()
        }
    }

    private fun login(email:String,password:String){
        var email=email
        var password=password
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task->
            if(task.isSuccessful){
                Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
        }.addOnFailureListener(){
            Toast.makeText(this,"Please enter correct details",Toast.LENGTH_SHORT).show()
        }
    }
}