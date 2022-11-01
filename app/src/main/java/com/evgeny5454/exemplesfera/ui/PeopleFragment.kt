package com.evgeny5454.exemplesfera.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeny5454.exemplesfera.adapters.PersonAdapter
import com.evgeny5454.exemplesfera.data.entities.Person
import com.evgeny5454.exemplesfera.data.repository.MockPersonRepository
import com.evgeny5454.exemplesfera.databinding.FragmentPeopleBinding

class PeopleFragment : Fragment() {

    private lateinit var binding: FragmentPeopleBinding
    private lateinit var adapter: PersonAdapter

    private val repository = MockPersonRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPeopleBinding.inflate(layoutInflater)
        initRecyclerView()
        initSubscribeItem()
        initUpButton()
        return binding.root
    }

    private fun initUpButton() {
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initSubscribeItem() {
        adapter.setOnItemClickListener { newItem ->
            val list = adapter.personList
            val newList = mutableListOf<Person>()
            list.forEach { oldItem ->
                if (oldItem.id == newItem.id) {
                    newList.add(newItem)
                } else {
                    newList.add(oldItem)
                }
            }
            adapter.personList = newList
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI(repository.getPersonList())
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        adapter = PersonAdapter(requireContext())
        recyclerView.adapter = adapter
    }

    /**
     * updateUI - один раз получает список контактов из репозиитория
     */
    private fun updateUI(personList: List<Person>) {
        adapter.personList = personList
    }
}