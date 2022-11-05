package com.evgeny5454.exemplesfera.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.evgeny5454.exemplesfera.R
import com.evgeny5454.exemplesfera.data.entities.Person
import com.evgeny5454.exemplesfera.databinding.ItemPersonBinding
import javax.inject.Inject

class UserListAdapter @Inject constructor(
    private val glide: RequestManager
) : ListAdapter<Person, UserListAdapter.ItemHolder>(DiffUtilCallback) {

    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(person: (Int) -> Unit) {
        onItemClickListener = person
    }

    inner class ItemHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val context = itemView.context
        fun bind(person: Person) = with(binding) {
            glide.load(person.photoUrl).into(userImage)
            userFullName.text = person.fullName
            if (person.isSubscribe) {
                isSubscribe.text = context.resources.getText(R.string.subscribe)
                isSubscribe.setTextColor(context.resources.getColor(R.color.stroke_color))
            } else {
                isSubscribe.text = context.resources.getText(R.string.unsubsribe)
                isSubscribe.setTextColor(context.resources.getColor(R.color.light_grey))
            }
            isSubscribe.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(person.id - 1)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemPersonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object DiffUtilCallback : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun getChangePayload(oldItem: Person, newItem: Person): Any? {
            return if (oldItem.isSubscribe != newItem.isSubscribe) true else null
        }
    }
}