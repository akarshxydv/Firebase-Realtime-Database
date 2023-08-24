package com.example.loginsignupfrd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginsignupfrd.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupA : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //firebaseDatabase=FirebaseDatabase.getInstance()
        //databaseReference=FirebaseDatabase.getInstance().getReference("User")
        auth= FirebaseAuth.getInstance()
        binding.loginbtn.setOnClickListener(){
            var name=binding.name.text.toString()
            var email=binding.Email.text.toString()
            var pass=binding.Password.text.toString()
            databaseReference=FirebaseDatabase.getInstance().getReference("User")
            var userData=userData(name,email,pass)
            //signUp(email,pass)
            databaseReference.child(name).setValue(userData).addOnCompleteListener {
                binding.name.text.clear()
                binding.Email.text.clear()
                binding.Password.text.clear()

            }
            signUp(email,pass)
            startActivity(Intent(this,login::class.java))
            finish()

        }
        binding.login.setOnClickListener(){
            startActivity(Intent(this,login::class.java))
            finish()
        }

    }

    private fun signUp(email:String,password:String){
            var email=email
        var password=password
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task->
            if(task.isSuccessful){
                Toast.makeText(this,"You have been register",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener(){
            Toast.makeText(this,it.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }
}