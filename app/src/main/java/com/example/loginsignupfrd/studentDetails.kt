package com.example.loginsignupfrd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginsignupfrd.databinding.ActivityStudentDetailsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class studentDetails : AppCompatActivity() {
    private lateinit var binding:ActivityStudentDetailsBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setValueToViews()

        binding.delete.setOnClickListener(){
            var reg=binding.registration.text.toString()
            deleteRecord(reg)
        }

        binding.update.setOnClickListener(){
            var reg=binding.registration.text.toString()
            var name=binding.name.text.toString()
            var course=binding.course.text.toString()
            databaseReference=FirebaseDatabase.getInstance().getReference("StudentsInfo")
            var studentInfo=StudentInfo(reg,name,course)
            databaseReference.child(reg).setValue(studentInfo).addOnSuccessListener(){
                Toast.makeText(this,"Student Data Updated",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,CheckDetails::class.java))
                finish()
            }
        }
    }

private fun deleteRecord(reg:String){
    databaseReference=FirebaseDatabase.getInstance().getReference("StudentsInfo").child(reg)
    val mtask=databaseReference.removeValue()
    mtask.addOnSuccessListener(){
        Toast.makeText(this,"Student Data Deleted",Toast.LENGTH_SHORT).show()
        startActivity(Intent(this,CheckDetails::class.java))
        finish()
    }.addOnFailureListener(){
        Toast.makeText(this,it.localizedMessage,Toast.LENGTH_SHORT).show()
    }
}

    private fun setValueToViews(){
        binding.registration.text=intent.getStringExtra("StuReg")
        binding.name.setText(intent.getStringExtra("StuName"))
        binding.course.setText(intent.getStringExtra("StuCourse"))
    }
}