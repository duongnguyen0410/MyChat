package com.example.mychat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mychat.BottomNav.ChatFragment
import com.example.mychat.BottomNav.ContactFragment
import com.example.mychat.BottomNav.ProfileFragment
import com.example.mychat.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ChatFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_chat -> replaceFragment(ChatFragment())
                R.id.ic_contact -> replaceFragment(ContactFragment())
                R.id.ic_profile -> replaceFragment(ProfileFragment())

            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }
}