package com.evgeny5454.exemplesfera.ui.people_pages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

    private val viewModel: UserViewModel by activityViewModels()
    //lateinit var viewModel: UserViewModel


    @Inject
    lateinit var adapter: UserListAdapter

    //@Inject
    // lateinit var menuItem: MenuItem

    private var download = false
    private var updateSearch = false
    private var searchQuery = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filter = arguments?.getString(FILTER).toString()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubcribersPeopleBinding.inflate(layoutInflater)
        //viewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        initObservers()
        initRecyclerView()
        adapter.setOnItemClickListener { user ->
            viewModel.updateUser(user)
          /*  if (searchQuery.isNotEmpty()) {
                updateSearch = false
                val newList = mutableListOf<UserTwo>()
                val oldList = adapter.currentList
                oldList.forEach {oldItem ->
                    if (oldItem.id == user.id) {
                        newList.add(user)
                    } else {
                        newList.add(oldItem)
                    }
                }
                adapter.submitList(newList)
            }*/
        }
        return binding.root
    }

    private fun initObservers() {
        viewModel.download.observe(viewLifecycleOwner) {
            download = it
        }

        viewModel.getAllRepositoryList().observe(viewLifecycleOwner) {
            if (searchQuery.isEmpty() /*|| updateSearch*/) {
                Log.d("SEARCH_VIEW", "при поиске это не выполняется")
                updateUI(it)
           }
        }
        initSearchView()
    }

    private fun initSearchView() {

        viewModel.searchText.observe(viewLifecycleOwner) {
            searchQuery = it
            updateSearch = true
            viewModel.search(searchQuery).observe(viewLifecycleOwner) { list ->
                Log.d("SEARCH_VIEW", "при поиске это выполняется! search = $updateSearch")
                if (updateSearch) {
                    updateUI(list)
                }
            }
        }
    }


    private fun updateUI(userList: List<UserTwo>) {
        if (userList.isEmpty() && !download) {
            viewModel.setUserList()
        }
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
        //lateinit var menuItem: MenuItem
    }
}