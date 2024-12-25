package com.example.benyawan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //เชื่อมปุ่ม Back
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish() //ให้มันกลับมาหน้าเดิมหรือหน้าก่อนหน้า -> MainActivity
        }

        //เมื่อกดปุ่ม submit จะทำการไปหน้า home
        val SubmitButton = findViewById<Button>(R.id.btnSubmit)
        SubmitButton.setOnClickListener {
            val intent = Intent(this@login, Homepage::class.java) //เมื่อกดปุุ่ม Submit แล้วจะไปหน้า Homepage
            startActivity(intent)
        }
    }
}