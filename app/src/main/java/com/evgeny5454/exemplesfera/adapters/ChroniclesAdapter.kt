package com.evgeny5454.exemplesfera.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evgeny5454.exemplesfera.databinding.ItemChronicBinding

class ChroniclesAdapter : RecyclerView.Adapter<ChroniclesAdapter.PhotoViewHolder>() {

    private val photosList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)

    class PhotoViewHolder(binding: ItemChronicBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemChronicBinding.inflate(layoutInflater, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        //noting to do
    }
}