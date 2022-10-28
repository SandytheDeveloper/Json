package com.example.json

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var con:Context,var img:ArrayList<String>,var name:ArrayList<String>,var age:ArrayList<String>):
RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    inner class MyViewHolder(iv: View) : RecyclerView.ViewHolder(iv){
        var image : ImageView=iv.findViewById(R.id.img) as ImageView
        var name : TextView=iv.findViewById(R.id.name) as TextView
        var age : TextView=iv.findViewById(R.id.age) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v=LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false)

        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val imgDraw = con.resources.getIdentifier(img[position],"drawable",con.packageName)
        holder.image.setImageResource(imgDraw)
        holder.name.text=name[position]
        holder.age.text=age[position]

        holder.itemView.setOnClickListener {
            Toast.makeText(con, name[position], Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return name.size
    }
}