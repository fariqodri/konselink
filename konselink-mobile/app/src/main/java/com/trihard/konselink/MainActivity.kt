package com.trihard.konselink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.trihard.konselink.fragments.PanduanFragment
import com.trihard.konselink.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    val panduanFragment by lazy {
        PanduanFragment.newInstance()
    }

    val profileFragment by lazy {
        ProfileFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(PanduanFragment())
        bottom_navbar.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment: Fragment
        Log.d("Item", item.itemId.toString())
        when(item.itemId) {
            R.id.panduan_menu -> {
                fragment = panduanFragment
            }
            R.id.profile_menu -> {
                fragment = profileFragment
            }
            else -> {
                // TODO THROW EXCEPTION
                fragment = panduanFragment
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
