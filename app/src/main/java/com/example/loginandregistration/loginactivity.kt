package com.example.loginandregistration
import android.widget.*
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Loginactivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val backtoregister = findViewById<TextView>(R.id.createaccount_login)
        backtoregister.setOnClickListener {
            val intent3 = Intent(this, MainActivity::class.java)
            startActivity(intent3)
        }
        val login = findViewById<Button>(R.id.button_login)
        login.setOnClickListener {
            val email = findViewById<EditText>(R.id.email_login)
            val email_text = email.text.toString()
            val password = findViewById<EditText>(R.id.password_login)
            val pass_text = password.text.toString()
            if (email_text.isEmpty() || pass_text.isEmpty()){
                Toast.makeText(this, "Please fill out email/pw.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email_text, pass_text)
                .addOnCompleteListener {
                    if (! it.isSuccessful) return@addOnCompleteListener

                    val intent1 = Intent(this, Afterlogin::class.java)
                        //intent1.flags =
                            //Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent1)
                    }

                .addOnFailureListener {
                     Toast.makeText(this, "Failed to log in: ${it.message}", Toast.LENGTH_SHORT).show()
                 }
        }
    //log.d(tag:"MainActivity", msg:"Email" + email)

       val resetpassword = findViewById<TextView>(R.id.reset_password)
        resetpassword.setOnClickListener {
            val intentreset = Intent(this, Reset::class.java)
            startActivity(intentreset)
        }


}}