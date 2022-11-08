package com.evgeny5454.exemplesfera.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.evgeny5454.exemplesfera.R
import com.evgeny5454.exemplesfera.data.entities.User
import com.evgeny5454.exemplesfera.databinding.ItemPersonBinding
import javax.inject.Inject

class UserListAdapter @Inject constructor(
    private val glide: RequestManager
) : ListAdapter<User, UserListAdapter.ItemHolder>(DiffUtilCallback) {

    private var onItemClickListener: ((User) -> Unit)? = null

    fun setOnItemClickListener(user: (User) -> Unit) {
        onItemClickListener = user
    }

    inner class ItemHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val context = itemView.context
        fun bind(user: User) = with(binding) {
            glide.load(user.photoUrl).into(userImage)
            userFullName.text = user.fullName
            if (user.isSubscribe) {
                isSubscribe.text = context.resources.getText(R.string.subscribe)
                isSubscribe.setTextColor(context.resources.getColor(R.color.stroke_color))
            } else {
                isSubscribe.text = context.resources.getText(R.string.unsubsribe)
                isSubscribe.setTextColor(context.resources.getColor(R.color.light_grey))
            }
            isSubscribe.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(
                        User(
                            id = user.id,
                            fullName = user.fullName,
                            isSubscribe = !user.isSubscribe,
                            subscribeMe = user.subscribeMe,
                            photoUrl = user.photoUrl
                        )
                    )
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

    private object DiffUtilCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun getChangePayload(oldItem: User, newItem: User): Any? {
            return if (oldItem.isSubscribe != newItem.isSubscribe) true else null
        }
    }
}