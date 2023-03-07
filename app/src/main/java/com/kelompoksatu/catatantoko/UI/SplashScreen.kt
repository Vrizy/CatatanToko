package com.kelompoksatu.catatantoko.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.kelompoksatu.catatantoko.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val intent = Intent(this, LoginScreen::class.java)
        Handler(
            Looper.getMainLooper()
        ).postDelayed({
            startActivity(intent)
        },4000)
    }
}