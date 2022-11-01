package com.evgeny5454.exemplesfera.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.evgeny5454.exemplesfera.R
import com.evgeny5454.exemplesfera.data.entities.Person
import com.evgeny5454.exemplesfera.databinding.ItemPersonBinding

class PersonAdapter(private val context: Context) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    open class PersonViewHolder(val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var personList: List<Person>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPersonBinding.inflate(inflater, parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = personList[position]
        with(holder.binding) {
            userFullName.text = person.fullName
            if (person.isSubscribe) {
                isSubscribe.text = context.resources.getText(R.string.subscribe)
                isSubscribe.setTextColor(context.resources.getColor(R.color.stroke_color))
            } else {
                isSubscribe.text = context.resources.getText(R.string.unsubsribe)
                isSubscribe.setTextColor(context.resources.getColor(R.color.light_grey))
            }

            Glide.with(context)
                .load(person.photoUrl)
                .centerCrop()
                .into(userImage)

            isSubscribe.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(
                        Person(
                            id = person.id,
                            fullName = person.fullName,
                            isSubscribe = !person.isSubscribe,
                            photoUrl = person.photoUrl
                        )
                    )
                }
            }
        }
    }

    private var onItemClickListener: ((Person) -> Unit)? = null

    fun setOnItemClickListener(listener: (Person) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return personList.size
    }
}