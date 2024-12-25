package com.example.benyawan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // เชื่อมปุ่ม Login
        val loginButton = findViewById<Button>(R.id.btnLogin)
        loginButton.setOnClickListener {//เมื่อกดปุ่ม login จะไปหน้า login
            val intent = Intent(this@MainActivity, login::class.java) // กดปุ่ม login แล้วไปหน้า login
            startActivity(intent)
        }

        // เชื่อมปุ่ม Sign in
        val signinButton = findViewById<Button>(R.id.btnSignin)
        signinButton.setOnClickListener { //กดปุ่มหน้า sign in จะไปหน้า sign in
            val intent = Intent(this@MainActivity, Signin::class.java) // กดปุ่ม sign in แล้วไปหน้า sign in
            startActivity(intent)
        }

        // เชื่อมปุ่ม Guests
        val guestsButton = findViewById<Button>(R.id.btnGuests)
        guestsButton.setOnClickListener { //เมื่อกดปุ่ม guests จะไปหน้า homepage
            val intent = Intent(this@MainActivity, Homepage::class.java) // กดปุ่ม guests แล้วไปหน้า homepage
            startActivity(intent)
        }
    }
}
