package com.trihard.konselink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.trihard.konselink.fragments.PanduanFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(PanduanFragment())
        bottom_navbar.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment: Fragment
        when(item.itemId) {
            0 -> {
                fragment = PanduanFragment()
            }
            else -> {
                throw Exception("Invalid navigation item!")
            }
        }
        return loadFragment(fragment)
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_placeholder, fragment)
            .commit()
        return true
    }
}
