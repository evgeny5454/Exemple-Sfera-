package com.evgeny5454.exemplesfera.adapters

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import com.evgeny5454.exemplesfera.R


class MomentsAdapter(private val list: List<Int>, context: Context) :
    BaseAdapter(R.layout.item_moment, list) {

    private val dp16 = 16.toDp(context)
    private val dp4 = 4.toDp(context)

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = holder.itemView.findViewById<View>(R.id.moment_view)
        when {
            list[position] == list.first() -> setMargin(item, margin_start = dp16)
            list[position] == list.last() -> setMargin(item, margin_start = dp4, margin_end = dp16)
            else -> setMargin(item, margin_start = dp4)
        }
    }

    private fun Int.toDp(context: Context): Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics
    ).toInt()

    private fun setMargin(item: View, margin_start: Int = 0, margin_end: Int = 0) {
        val params = (item.layoutParams as ViewGroup.MarginLayoutParams).apply {
            setMargins(margin_start, 0, margin_end, 0)
        }
        item.layoutParams = params
    }
}