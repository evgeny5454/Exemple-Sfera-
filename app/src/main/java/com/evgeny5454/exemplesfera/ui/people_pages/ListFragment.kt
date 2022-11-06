package com.evgeny5454.exemplesfera.ui.people_pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeny5454.exemplesfera.adapters.UserListAdapter
import com.evgeny5454.exemplesfera.data.model.UserTwo
import com.evgeny5454.exemplesfera.databinding.FragmentSubcribersPeopleBinding
import com.evgeny5454.exemplesfera.other.Constants
import com.evgeny5454.exemplesfera.view_model.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val FILTER = "filter_string"

@AndroidEntryPoint
class ListFragment() : Fragment() {
    private lateinit var binding: FragmentSubcribersPeopleBinding
    private lateinit var filter: String

    private val viewModel: UserViewModel by viewModels()

    @Inject
    lateinit var adapter: UserListAdapter

    //@Inject
    // lateinit var menuItem: MenuItem

    private var download = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filter = arguments?.getString(FILTER).toString()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubcribersPeopleBinding.inflate(layoutInflater)
        initObservers()
        initRecyclerView()
        adapter.setOnItemClickListener { user ->
            viewModel.updateUser(user)
        }
        return binding.root
    }

    private fun initObservers() {
        viewModel.download.observe(viewLifecycleOwner) {
            download = it
        }

        viewModel.getAllRepositoryList().observe(viewLifecycleOwner) {
            updateUI(it)
        }
        initSearchView()
    }

    private fun initSearchView() {

        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.search(newText).observe(viewLifecycleOwner) { list ->
                        updateUI(list)
                    }
                }
                return false
            }
        })

    }


    private fun updateUI(userList: List<UserTwo>) {
        if (userList.isEmpty() && !download) {
            viewModel.setUserList()
        }
        //viewModel.tempList(userList)
        when (filter) {
            Constants.NO_FILTER -> {
                adapter.submitList(userList)
            }
            Constants.SUBSCRIBE_TO_ME -> {
                val list = userList.filter { it.subscribeMe }
                adapter.submitList(list)
            }
            Constants.MUTUALLY -> {
                val list = userList.filter { it.subscribeMe && it.isSubscribe }
                adapter.submitList(list)
            }
        }
        viewModel.setDownloadState()
    }


    private fun initRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance(filter: String): ListFragment {
            return ListFragment().apply {
                arguments = Bundle().apply {
                    putString(FILTER, filter)

                }
            }
        }
        /*fun newInstance(filter: String, menuItem: MenuItem): ListFragment {
            return ListFragment(menuItem).apply {
                arguments = Bundle().apply {
                    putString(FILTER, filter)

                }
            }
        }*/

        lateinit var menuItem: MenuItem
    }
}