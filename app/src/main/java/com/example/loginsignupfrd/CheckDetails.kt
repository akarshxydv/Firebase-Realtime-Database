
package com.example.loginsignupfrd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginsignupfrd.databinding.ActivityCheckDetailsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CheckDetails : AppCompatActivity() {
    private lateinit var binding:ActivityCheckDetailsBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var StuList:ArrayList<StudentInfo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCheckDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvStu.layoutManager=LinearLayoutManager(this)
        binding.rvStu.setHasFixedSize(true)


        StuList= arrayListOf<StudentInfo>()

        getStudentData()

    }

    private fun getStudentData(){
        binding.rvStu.visibility=View.GONE
        binding.tvload.visibility=View.VISIBLE

        databaseReference=FirebaseDatabase.getInstance().getReference("StudentsInfo")

        databaseReference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                StuList.clear()
                if(snapshot.exists()){
                    for(stuSnap in snapshot.children){
                        val studentData=stuSnap
                            .getValue(StudentInfo::class.java)
                        StuList.add(studentData!!)
                    }

                    val mAdapter=recyclerAdapter(StuList)
                    binding.rvStu.adapter=mAdapter
                    mAdapter.setOnItemClickListener(object:recyclerAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent= Intent(this@CheckDetails,studentDetails::class.java)

                            // put Extra
                            intent.putExtra("StuReg",StuList[position].registration)
                            intent.putExtra("StuName",StuList[position].name)
                            intent.putExtra("StuCourse",StuList[position].course)

                            startActivity(intent)
                        }
                    })
                    binding.rvStu.visibility=View.VISIBLE
                    binding.tvload.visibility=View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}