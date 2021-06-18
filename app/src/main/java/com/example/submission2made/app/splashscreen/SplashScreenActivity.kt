package com.example.submission2made.app.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.submission2made.R
import com.example.submission2made.app.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        //menutup activity splashcreen dengan delay 1 detik dan menjalankan main activity
        Handler(Looper.getMainLooper()).postDelayed({
            val mainActivityIntent = Intent(this, HomeActivity::class.java)
            startActivity(mainActivityIntent)
            finish()
        }, 1000)
    }
}