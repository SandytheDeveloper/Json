package com.example.json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    var img :ArrayList<String> = ArrayList()
    var name:ArrayList<String> = ArrayList()
    var age:ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView=findViewById<RecyclerView>(R.id.recycleview)
        val LinearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = LinearLayoutManager
        try {
            val obj = JSONObject(loadJSONFromAssets())
            val emps = obj.getJSONArray("user")
            for (i in 0 until emps.length()){
                val user = emps.getJSONObject(i)
                img.add(user.getString("img"))
                name.add(user.getString("name"))
                age.add(user.getString("age"))
            }
        }catch (e:IOException){
            e.printStackTrace()
        }

        val customAdapter = MyAdapter(this@MainActivity,img,name,age)
        recyclerView.adapter = customAdapter
    }

    fun loadJSONFromAssets(): String{
        val json:String

        try {
            val inputStream = assets.open("user.json")
            val size = inputStream.available()
            val buffer=ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer,charset)

            return json
        } catch (e:IOException){
            e.printStackTrace()
            return ""
        }
    }
}