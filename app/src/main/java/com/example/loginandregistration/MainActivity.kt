package com.example.loginandregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.util.Log
import android.widget.*
import android.widget.Button
import android.widget.TextView
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val register = findViewById<Button>(R.id.button_register)
        register.setOnClickListener {
            val email = findViewById<EditText>(R.id.register_edittext_editemail)
            val email_text = email.text.toString()
            val password = findViewById<EditText>(R.id.register_edittext_editepw)
            val pass_text = password.text.toString()
            if (email_text.isEmpty() || pass_text.isEmpty()) {
                Toast.makeText(this, "Please enter valid entry", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email_text, pass_text)
                .addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        val intent1 = Intent(this, Loginactivity::class.java)
                        startActivity(intent1)
                    }

                   /* else
                    {
                        val intent2 = Intent(this, MainActivity::class.java)
                        startActivity(intent2)
                    }
*/

                }
                .addOnFailureListener {
                    Toast.makeText(this, "failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }

        //Log.d(tag:"MainActivity", msg:"Email" + email)
        val alreadyaccount = findViewById<TextView>(R.id.alreadyaccount)
        alreadyaccount.setOnClickListener {
            val intent = Intent(this, Loginactivity::class.java)
            startActivity(intent)
        }
        /*val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, listOf("None", "Top", "Bottom"))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter
        */


    }
}