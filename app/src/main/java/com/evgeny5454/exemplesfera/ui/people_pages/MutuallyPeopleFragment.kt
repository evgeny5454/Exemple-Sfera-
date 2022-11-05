package com.evgeny5454.exemplesfera.ui.people_pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.evgeny5454.exemplesfera.databinding.FragmentSubcribersPeopleBinding


class MutuallyPeopleFragment : Fragment() {
    private lateinit var binding: FragmentSubcribersPeopleBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubcribersPeopleBinding.inflate(layoutInflater)

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MutuallyPeopleFragment()
    }
}