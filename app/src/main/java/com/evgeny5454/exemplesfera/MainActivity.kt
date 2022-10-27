package com.evgeny5454.exemplesfera

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.evgeny5454.exemplesfera.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}