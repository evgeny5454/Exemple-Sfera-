package com.evgeny5454.exemplesfera.adapters

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.print.PrintAttributes.Margins
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.evgeny5454.exemplesfera.R
import com.evgeny5454.exemplesfera.databinding.ItemMomentBinding

class MomentsAdapter(private val list: List<Int>, private val context: Context) : RecyclerView.Adapter<MomentsAdapter.PhotoViewHolder>() {

    private val dp16 = 16.toDp(context)
    private val dp4 = 4.toDp(context)


    class PhotoViewHolder(val binding: ItemMomentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMomentBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

        with(holder.binding) {
            if (list[position] == list.first()){
                val params = (momentView.layoutParams as ViewGroup.MarginLayoutParams).apply {
                    setMargins(dp16, 0, 0, 0)
                }
                momentView.layoutParams = params

            } else if (list[position] == list.last()) {
                val params = (momentView.layoutParams as ViewGroup.MarginLayoutParams).apply {
                    setMargins(dp4, 0, dp16, 0)
                }
                momentView.layoutParams = params
            } else {
                val params = (momentView.layoutParams as ViewGroup.MarginLayoutParams).apply {
                    setMargins(dp4, 0, 0, 0)
                }
                momentView.layoutParams = params
            }
        }
    }
    private fun Int.toDp(context: Context):Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        context.resources.displayMetrics
    ).toInt()
}