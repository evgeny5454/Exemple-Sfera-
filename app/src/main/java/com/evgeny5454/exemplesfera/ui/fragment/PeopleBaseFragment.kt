package com.evgeny5454.exemplesfera.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.evgeny5454.exemplesfera.R
import com.evgeny5454.exemplesfera.adapters.PeopleViewPagerAdapter
import com.evgeny5454.exemplesfera.databinding.FragmentPeopleBinding
import com.evgeny5454.exemplesfera.ui.people_pages.MutuallyPeopleFragment
import com.evgeny5454.exemplesfera.ui.people_pages.SubscribersPeopleFragment
import com.evgeny5454.exemplesfera.ui.people_pages.ListFragment
import com.google.android.material.tabs.TabLayoutMediator

class PeopleBaseFragment : Fragment() {

    private lateinit var binding: FragmentPeopleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPeopleBinding.inflate(layoutInflater)
        initNavigation()
        initViewPager()
        return binding.root
    }

    private fun initViewPager() {
        val viewPager = binding.viewPager2
        val fragmentList = listOf(
            ListFragment.newInstance(),
            ListFragment.newInstance(),
            ListFragment.newInstance()
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
    }
}