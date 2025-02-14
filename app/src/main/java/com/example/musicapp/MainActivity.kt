//package com.example.musicapp
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.navigation.Navigation.findNavController
//import androidx.navigation.findNavController
//import androidx.navigation.ui.setupWithNavController
//import com.google.android.material.bottomnavigation.BottomNavigationView
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val navController = findNavController(R.id.nav_host_fragment)
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
//        bottomNavigationView.setupWithNavController(navController)
//    }
//}

package com.example.musicapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.musicapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Настройка навигации
        navController = findNavController(R.id.nav_host_fragment)

        // Конфигурация для ActionBar (если используется)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.localTracksFragment, R.id.apiTracksFragment)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Связывание BottomNavigation с NavController
        binding.bottomNavigation.setupWithNavController(navController)
    }

    // Обработка кнопки "Назад" в ActionBar (если нужно)
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}