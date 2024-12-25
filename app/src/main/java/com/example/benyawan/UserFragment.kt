package com.example.benyawan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        // ปุ่ม Log out
        val btnLogOut = view.findViewById<Button>(R.id.btnSignin)

        btnLogOut.setOnClickListener {
            // เปลี่ยนไปหน้า Home (MainActivity)
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)

            // ถ้าต้องการจบ Activity ปัจจุบัน (ถ้ามี)
            activity?.finish()
        }

        return view
    }
}
