package com.a706012110039.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.a706012110039.signup.databinding.ActivitySplash2Binding

import android.animation.*
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView

class SplashActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivitySplash2Binding;
    lateinit var star: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)
        supportActionBar?.hide()
        binding = ActivitySplash2Binding.inflate(layoutInflater)
        binding.imageView2.alpha = 0f

        star = findViewById(R.id.imageView2)

        animate()
    }

    private fun animate()
    {
        val animator = ObjectAnimator.ofFloat(star, View.ALPHA, 0f)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }
}