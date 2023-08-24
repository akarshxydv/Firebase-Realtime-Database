package com.example.loginsignupfrd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginsignupfrd.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

//data class StudentInfo(
//    var name:String?=null,
//    var course:String?=null,
//    var registration:String?=null
//)
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var databaseref:DatabaseReference
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()

        binding.submit.setOnClickListener(){
            var name=binding.name.text.toString()
            var course=binding.course.text.toString()
            var registration=binding.regis.text.toString()
            databaseref=FirebaseDatabase.getInstance().getReference("StudentsInfo")
            var studentInfo=StudentInfo(registration, name, course)
            databaseref.child(registration).setValue(studentInfo).addOnCompleteListener {
                binding.name.text.clear()
                binding.course.text.clear()
                binding.regis.text.clear()
                Toast.makeText(this,"Details Submitted",Toast.LENGTH_SHORT).show()
            }
        }

        //
        binding.showdata.setOnClickListener(){
            startActivity(Intent(this,CheckDetails::class.java))
        }



        binding.logout.setOnClickListener(){
            auth.signOut()
            startActivity(Intent(this,login::class.java))
            finish()
        }


    }
}