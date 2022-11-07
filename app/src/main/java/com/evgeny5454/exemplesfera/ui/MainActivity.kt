package com.evgeny5454.exemplesfera.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.evgeny5454.exemplesfera.databinding.ActivityMainBinding
import com.evgeny5454.exemplesfera.view_model.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //val userViewModel : UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       /* userViewModel.searchText.observe(this) {
            Log.d("MainActivityTEXT", it)
        }*/
    }
}