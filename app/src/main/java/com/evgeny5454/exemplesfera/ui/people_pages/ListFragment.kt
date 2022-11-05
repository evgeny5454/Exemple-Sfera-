package com.evgeny5454.exemplesfera.ui.people_pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeny5454.exemplesfera.adapters.UserListAdapter
import com.evgeny5454.exemplesfera.data.entities.Person
import com.evgeny5454.exemplesfera.data.repository.MockPersonRepository
import com.evgeny5454.exemplesfera.databinding.FragmentSubcribersPeopleBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment() {
    private lateinit var binding: FragmentSubcribersPeopleBinding

    private val repository = MockPersonRepository()

    @Inject
    lateinit var adapter: UserListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubcribersPeopleBinding.inflate(layoutInflater)
        initRecyclerView()
        adapter.setOnItemClickListener { id ->
            val list = adapter.currentList.toMutableList()
            val currentUser = list[id]
            val user = Person(
                id = currentUser.id,
                fullName = currentUser.fullName,
                isSubscribe = !currentUser.isSubscribe,
                photoUrl = currentUser.photoUrl
            )
            list[id] = user
            adapter.submitList(list)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.submitList(repository.getPersonList())
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}