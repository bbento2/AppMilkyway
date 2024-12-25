package com.example.benyawan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        // เรียกใช้ BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // กำหนดหน้าที่จะแสดงครั้งแรก
        loadFragment(HomeFragment())

        // เพิ่ม Listener ให้ BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment()) // แสดง Fragment หน้า Home
                    true
                }
                R.id.notification -> {
                    loadFragment(NotificationFragment()) // แสดง Fragment หน้า Notification
                    true
                }
                R.id.user -> {
                    loadFragment(UserFragment()) // แสดง Fragment หน้า User
                    true
                }
                else -> false
            }
        }
    }

    // ฟังก์ชันสำหรับเปลี่ยน Fragment
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }
}

