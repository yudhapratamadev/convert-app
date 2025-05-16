package com.c.myconverter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Atur fragment awal
        if (savedInstanceState == null) {
            loadFragment(TempFragment())
        }

        // Pengaturan listener bottom navigation
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_temp -> {
                    loadFragment(TempFragment())
                    true
                }
                R.id.nav_distance -> {
                    loadFragment(DistanceFragment())
                    true
                }
                R.id.nav_weight -> {
                    loadFragment(WeightFragment())
                    true
                }
                R.id.nav_volume -> {
                    loadFragment(VolumeFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}