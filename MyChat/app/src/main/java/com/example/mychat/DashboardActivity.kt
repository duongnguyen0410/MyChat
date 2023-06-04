package com.example.mychat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mychat.BottomNav.ChatFragment
import com.example.mychat.BottomNav.ContactFragment
import com.example.mychat.BottomNav.ProfileFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar

private var fragment: Fragment? = null

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val chip = findViewById<ChipNavigationBar>(R.id.menu);

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, ChatFragment())
                .commit()
            chip.setItemSelected(R.id.btn_chat)
        }

        chip.setOnItemSelectedListener { id ->
            when (id) {
                R.id.btn_chat -> {
                    fragment = ChatFragment()
                }

                R.id.btn_contact -> {
                    fragment = ContactFragment()
                }

                R.id.btn_profile -> {
                    fragment = ProfileFragment()
                }
            }
            fragment!!.let {
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment!!)
                    .commit()
            }
        }


    }
}