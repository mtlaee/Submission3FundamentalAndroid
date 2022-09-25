package com.example.submission2aplikasigithubtaufikakbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.submission2aplikasigithubtaufikakbar.DarkMode.DarkModeViewModel
import com.example.submission2aplikasigithubtaufikakbar.DarkMode.DarkModeViewModelFactory
import com.example.submission2aplikasigithubtaufikakbar.DarkMode.SettingPreferences
import com.example.submission2aplikasigithubtaufikakbar.DarkMode.dataStore



class SplashScreen : AppCompatActivity() {
    val delay = 2500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val pref = SettingPreferences.getInstance(dataStore)
        val mainViewModel = ViewModelProvider(this, DarkModeViewModelFactory(pref)).get(
            DarkModeViewModel::class.java
        )
        mainViewModel.getThemeSettings().observe(this,
            { isDarkModeActive: Boolean ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            })
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        }, delay.toLong())
    }
}