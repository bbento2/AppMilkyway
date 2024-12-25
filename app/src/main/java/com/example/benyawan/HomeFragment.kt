package com.example.benyawan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.imageview.ShapeableImageView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_homepage, container, false)

        // Find the ImageViews
        val moodengShapeableImageView: ShapeableImageView = view.findViewById(R.id.ShapeableImageView)
        val playlistImageView: ImageView = view.findViewById(R.id.playlist1)

        //เมื่อคลิกรูปหมูเด้งจะไปหน้า userfragment
        moodengShapeableImageView.setOnClickListener {
            navigateToUserFragment()
        }

        // เมื่อคลิกรูป playlist1 แล้วจะไปหน้า FreetimeFragment
        playlistImageView.setOnClickListener {
            navigateToFreetimeFragment()
        }

        return view
    }

    private fun navigateToUserFragment() {
        //  เมื่อกดรูปหมูเด้งจะเปลี่ยนหน้าไปเป็น UserFragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_container, UserFragment())
            .addToBackStack(null) // Allow back navigation
            .commit()

        // Update the BottomNavigationView to highlight the "User" menu item
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.user

    }

    private fun navigateToFreetimeFragment() {
        // เมื่อกดรูป playlist1 แล้วจะเปลี่ยนหน้าไปเป็น FreetimeFragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_container, FreetimeFragment()) // Replace with FreetimeFragment
            .addToBackStack(null) // Allow back navigation
            .commit()

        // Update the BottomNavigationView to highlight the "Freetime" menu item
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
    }
}
