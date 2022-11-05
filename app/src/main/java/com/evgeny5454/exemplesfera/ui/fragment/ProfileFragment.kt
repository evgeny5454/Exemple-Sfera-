package com.evgeny5454.exemplesfera.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeny5454.exemplesfera.R
import com.evgeny5454.exemplesfera.adapters.AddPhotoAdapter
import com.evgeny5454.exemplesfera.adapters.ChroniclesAdapter
import com.evgeny5454.exemplesfera.adapters.MomentsAdapter
import com.evgeny5454.exemplesfera.databinding.FragmentProfileBinding

private const val ADD_PHOTO = 4
private const val MOMENTS = 8
private const val CHRONICLES = 12

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding


    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        initRecyclerView()
        initButtons()

        binding.editText.setOnFocusChangeListener { _, _ ->
            binding.textInputLayout.counterTextColor = getColorStateList(requireContext(), R.color.stroke_color)
        }

        return binding.root
    }

    private fun initButtons() {
        binding.btnPeople.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_peopleFragment)
        }
    }

    /**
     * suppressLayout - предотвращает возможность прокрутки layout
     * соотвественно при прокнутке экрана не цепляется за Recycler View
     */

    private fun initRecyclerView() {
        val addPhotos = binding.recyclerViewAddPhotos
        val moments = binding.recyclerViewMoments
        val chronicles = binding.recyclerViewChronicles

        val addPhotosLayoutManager = LinearLayoutManager(requireContext())
        addPhotosLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        addPhotos.layoutManager = addPhotosLayoutManager
        addPhotos.adapter = AddPhotoAdapter(List(ADD_PHOTO, init = { it }))
        addPhotos.suppressLayout(true)

        val momentsLayoutManager = LinearLayoutManager(requireContext())
        momentsLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        moments.layoutManager = momentsLayoutManager
        moments.adapter = MomentsAdapter(List(MOMENTS, init = { it }), requireContext())

        val chroniclesLayoutManager = GridLayoutManager(requireContext(), 3)
        chronicles.layoutManager = chroniclesLayoutManager
        chronicles.adapter = ChroniclesAdapter(List(CHRONICLES, init = { it }))
        chronicles.suppressLayout(true)
    }
}