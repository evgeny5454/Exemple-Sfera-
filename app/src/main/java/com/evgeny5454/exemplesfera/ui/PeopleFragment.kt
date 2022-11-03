package com.evgeny5454.exemplesfera.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeny5454.exemplesfera.R
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
        initNavigation()
        return binding.root
    }

    private fun initNavigation() {
        val toolbar = binding.toolbar
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        toolbar.title = activity?.resources?.getText(R.string._people)
        toolbar.setNavigationIcon(R.drawable.ic_back)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.personList = repository.getPersonList()
    }

    private fun initSubscribeItem() {
        adapter.setOnItemClickListener { position ->
            val list = adapter.personList.toMutableList()
            val user = list[position]
            list[position] = Person(
                id = user.id,
                fullName = user.fullName,
                isSubscribe = !user.isSubscribe,
                photoUrl = user.photoUrl
            )
            adapter.personList = list
        }
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        adapter = PersonAdapter(requireContext())
        recyclerView.adapter = adapter
    }
}