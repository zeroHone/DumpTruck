package com.example.dumptruck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            val user =findViewById<TextInputEditText>(R.id.txtUser).text.toString()
            val password = findViewById<TextInputEditText>(R.id.txtPass).text.toString()
            if(loginAttempt(user, password)){
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)


            }else{
                Toast.makeText(applicationContext, getString(R.string.login_error_message), Toast.LENGTH_LONG).show()
            }
        }
    }
    fun loginAttempt(user : String , pass: String):Boolean{
        return true //user == SettingsData.USERNAME && pass == SettingsData.PASSWORD
    }
}