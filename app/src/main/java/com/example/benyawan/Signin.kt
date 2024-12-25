package com.example.benyawan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val dbHelper = DatabaseHelper(this)


        btnSubmit.setOnClickListener {
            val edtEmail = findViewById<EditText>(R.id.edtEmail)
            val edtName = findViewById<EditText>(R.id.edtName)
            val edtAge = findViewById<EditText>(R.id.edtAge)
            val edtBD = findViewById<EditText>(R.id.edtBD)
            val edtUsername = findViewById<EditText>(R.id.edtUsername)
            val edtPassword = findViewById<EditText>(R.id.edtPassword)
            val radioGroupSex = findViewById<RadioGroup>(R.id.radioGroupSex)
            val checkboxTerms = findViewById<CheckBox>(R.id.checkboxTerms)

            val selectedRadioButtonId = radioGroupSex.checkedRadioButtonId
            val gender = when (selectedRadioButtonId) {
                R.id.radioMale -> "Male"
                R.id.radioFemale -> "Female"
                else -> ""
            }

            // ถ้ากดปุ่ม submit แล้ว user กรอกข้อมูลไม่ครบ จะขึ้นข้อความแจ้งเตือน
            if (edtEmail.text.isEmpty() || edtName.text.isEmpty() || edtAge.text.isEmpty() || edtBD.text.isEmpty() || edtUsername.text.isEmpty() || edtPassword.text.isEmpty() || gender.isEmpty() || !checkboxTerms.isChecked) {
                Toast.makeText(this, "Please fill in all the fields and accept the terms", Toast.LENGTH_SHORT).show()
            } else {
                // กรณีกรอกข้อมูลครบ ข้อมูลตะเข้า database
                dbHelper.addUser(
                    edtEmail.text.toString(),
                    edtName.text.toString(),
                    edtAge.text.toString().toInt(),
                    edtBD.text.toString(),
                    gender,
                    edtUsername.text.toString(),
                    edtPassword.text.toString()
                )

                // Retrieve all users after insert
                val users = dbHelper.getAllUsers()
                for (user in users) {
                    Log.d("DatabaseHelper", user)
                }


                // กรณีกรอกข้อมูลครบ จะขึ้นข้อความแจ้งเตือน
                Toast.makeText(this, "Sign in successfully", Toast.LENGTH_SHORT).show()

                // Log all users in Logcat
                for (user in users) {
                    Log.d("DatabaseHelper", user)
                }

                // Go to the login screen after successful sign in
                val intent = Intent(this@Signin, login::class.java)
                startActivity(intent)
                finish() // Close this activity
            }
        }
    }
}
