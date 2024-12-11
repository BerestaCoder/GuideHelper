package com.example.guidehelper.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.guidehelper.databinding.FragmentEventViewBinding

private const val NAME = "param1"
private const val DATE = "param2"
private const val PLACE = "param3"
private const val INFO = "param4"

class EventViewFragment : Fragment() {

    private lateinit var binding: FragmentEventViewBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventViewBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            binding.eventNameText.text = arguments?.getString("name").toString()
            binding.eventDateText.text = arguments?.getString("date").toString()
            binding.eventPlaceText.text = arguments?.getString("place").toString()
            binding.eventInfoText.text = arguments?.getString("info").toString()
        }

        init(view)
        registerEvents()
    }
    private fun init(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun registerEvents() {
        binding.backButton.setOnClickListener {
            navController.popBackStack()
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(name: String, date: String, place: String, info: String) =
            EventViewFragment().apply {
                arguments = Bundle().apply {
                    putString(NAME, name)
                    putString(DATE, date)
                    putString(PLACE, place)
                    putString(INFO, info)
                }
            }
    }
}