package com.example.infobyte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database :DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signbutton = findViewById<Button>(R.id.btnSSigned)
        val etname=findViewById<EditText>(R.id.etName)
        val etmail = findViewById<EditText>(R.id.etSEmailAddress)
        val userId =findViewById<EditText>(R.id.etusername)
        val userPassword =findViewById<EditText>(R.id.etSPassword)
        val redirect =findViewById<TextView>(R.id.tvRedirectLogin)

        redirect.setOnClickListener {
            val intent =Intent(this,loginActivity::class.java)
            startActivity(intent)
        }

        signbutton.setOnClickListener {

            val name = etname.text.toString()
            val mail =etmail.text.toString()
            val uniqueId = userId.text.toString()
            val password = userPassword.text.toString()

            val user=users(name,mail,password,uniqueId)
            database = FirebaseDatabase.getInstance().getReference("Users")

            database.child(uniqueId).setValue(user).addOnSuccessListener {
                etname.text.clear()
                Toast.makeText(this,"User Registered",Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }

        }
    }
}