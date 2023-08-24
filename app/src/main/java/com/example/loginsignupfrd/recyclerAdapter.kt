package com.example.loginsignupfrd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class recyclerAdapter(private val StudentList:ArrayList<StudentInfo>):RecyclerView.Adapter<recyclerAdapter.ViewHolder>() {


    private lateinit var mListener:onItemClickListener

    interface  onItemClickListener {
                fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener=clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ViewHolder(itemView,mListener)
    }

    override fun getItemCount(): Int {
        return StudentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentStu:StudentInfo=StudentList[position]
        holder.tvStudentName.text=currentStu.name
    }

  class ViewHolder(itemView: View, clickListener: onItemClickListener):RecyclerView.ViewHolder(itemView){
      val tvStudentName:TextView=itemView.findViewById(R.id.stuName)
      init {
          itemView.setOnClickListener(){
              clickListener.onItemClick(adapterPosition)
          }
      }
  }

}