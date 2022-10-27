package com.evgeny5454.exemplesfera

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeny5454.exemplesfera.adapters.AddPhotoAdapter
import com.evgeny5454.exemplesfera.adapters.ChroniclesAdapter
import com.evgeny5454.exemplesfera.adapters.MomentsAdapter
import com.evgeny5454.exemplesfera.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val addPhotos = binding.recyclerViewAddPhotos
        val moments = binding.recyclerViewMoments
        val chronicles = binding.recyclerViewChronicles

        val addPhotosLayoutManager = LinearLayoutManager(this)
        addPhotosLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        addPhotos.layoutManager = addPhotosLayoutManager
        addPhotos.adapter = AddPhotoAdapter()
        addPhotos.isLayoutFrozen = true

        val momentsLayoutManager = LinearLayoutManager(this)
        momentsLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        moments.layoutManager = momentsLayoutManager
        moments.adapter = MomentsAdapter()

        val chroniclesLayoutManager = GridLayoutManager(this, 3)
        chronicles.layoutManager = chroniclesLayoutManager
        chronicles.adapter = ChroniclesAdapter()
        chronicles.isLayoutFrozen = true
    }
}