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

class loginActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signInButton =findViewById<Button>(R.id.btnLogin)
        val username =findViewById<EditText>(R.id.userNamee)
        val redirectSignUp =findViewById<TextView>(R.id.tvRedirectSignUp)

        redirectSignUp.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        signInButton.setOnClickListener {

            val uniqueId =username.text.toString()
            if(uniqueId.isNotEmpty()){
                readData(uniqueId)
            }
            else{
                Toast.makeText(this,"please Eneter username",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun readData(uniqueId: String) {
            databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(uniqueId).get().addOnSuccessListener {

            if (it.exists()){
                //show intent
                val email= it.child("email").value
                val name =it.child("name").value
                val id = it.child("uniqueId").value

                val intent =Intent(this,WelcomActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"User does not exito",Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this,"user Doesn't exist",Toast.LENGTH_SHORT).show()
        }
    }
}