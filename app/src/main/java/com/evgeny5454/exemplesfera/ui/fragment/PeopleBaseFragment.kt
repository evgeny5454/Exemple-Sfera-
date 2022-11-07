package com.evgeny5454.exemplesfera.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.evgeny5454.exemplesfera.R
import com.evgeny5454.exemplesfera.adapters.PeopleViewPagerAdapter
import com.evgeny5454.exemplesfera.databinding.FragmentPeopleBinding
import com.evgeny5454.exemplesfera.other.Constants
import com.evgeny5454.exemplesfera.ui.people_pages.ListFragment
import com.evgeny5454.exemplesfera.view_model.UserViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleBaseFragment : Fragment() {

    private lateinit var binding: FragmentPeopleBinding
     private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPeopleBinding.inflate(layoutInflater)
        //viewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        initNavigation()
        initViewPager()
        return binding.root
    }

    private fun initViewPager() {
        val menu = binding.toolbar.menu.findItem(R.id.action_search)
        val viewPager = binding.viewPager2
        val fragmentList : List<Fragment> = listOf(
            ListFragment.newInstance(Constants.NO_FILTER),
            ListFragment.newInstance(Constants.SUBSCRIBE_TO_ME),
            ListFragment.newInstance(Constants.MUTUALLY)
        )
        val fragmentListTitles = listOf(
            getString(R.string.subscribers),
            getString(R.string.subscriptions),
            getString(R.string.mutually)
        )

        viewPager.adapter = PeopleViewPagerAdapter(requireActivity(), fragmentList)

        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            tab.text = fragmentListTitles[position]
        }.attach()
        initSearchView(menu)
    }

    private fun initSearchView(menuItem: MenuItem) {

        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    /*viewModel.search(newText).observe(viewLifecycleOwner) { list ->

                    }*/
                    viewModel.initSearchText(newText)
                }
                return false
            }
        })

    }

    private fun initNavigation() {
        val toolbar = binding.toolbar
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        toolbar.title = activity?.resources?.getText(R.string._people)
        toolbar.setNavigationIcon(R.drawable.ic_back)
    }
}