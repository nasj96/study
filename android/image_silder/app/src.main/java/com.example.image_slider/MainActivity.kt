package com.example.image_slider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.image_slider.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    internal lateinit var viewpager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //-- prev --
        //setContentView(R.layout.activity_main)

        //-- 4.1 --
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //-- prev --
        //viewpager = findViewById(R.id.viewpager) as ViewPager

        //-- 4.1 --
        viewpager = binding.viewpager as ViewPager

        val adapter = ViewPagerAdapter(this)
        viewpager.adapter = adapter
    }
}