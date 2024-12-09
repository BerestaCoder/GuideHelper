package com.example.guidehelper.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.guidehelper.R
import com.example.guidehelper.databinding.FragmentEventListBinding

class EventListFragment : Fragment() {

    private var binding: FragmentEventListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEventListBinding.inflate(inflater, container, false)

        val view = inflater.inflate(R.layout.fragment_event_list, container, false)

        // КНОПКИ
        view.findViewById<ImageButton>(R.id.add_event_button).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_register_new_event)
        }
        view.findViewById<ImageButton>(R.id.back_button).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_go_back)
        }
        return view
    }

}