package com.evgeny5454.exemplesfera.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.evgeny5454.exemplesfera.R
import com.evgeny5454.exemplesfera.data.model.UserTwo
import com.evgeny5454.exemplesfera.databinding.ItemPersonBinding
import javax.inject.Inject

class UserListAdapter @Inject constructor(
    private val glide: RequestManager
) : ListAdapter<UserTwo, UserListAdapter.ItemHolder>(DiffUtilCallback) {

    private var onItemClickListener: ((UserTwo) -> Unit)? = null

    fun setOnItemClickListener(user: (UserTwo) -> Unit) {
        onItemClickListener = user
    }

    inner class ItemHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val context = itemView.context
        fun bind(user: UserTwo) = with(binding) {
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
                        UserTwo(
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

    private object DiffUtilCallback : DiffUtil.ItemCallback<UserTwo>() {
        override fun areItemsTheSame(oldItem: UserTwo, newItem: UserTwo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserTwo, newItem: UserTwo): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun getChangePayload(oldItem: UserTwo, newItem: UserTwo): Any? {
            return if (oldItem.isSubscribe != newItem.isSubscribe) true else null
        }
    }
}