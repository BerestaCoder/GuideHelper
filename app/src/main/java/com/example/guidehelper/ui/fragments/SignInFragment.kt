package com.example.guidehelper.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.guidehelper.R
import com.example.guidehelper.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var navControl: NavController
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        registerEvents()
    }

    private fun init(view: View) {
        navControl = Navigation.findNavController(view)
    }
    private fun registerEvents() {
        binding.enter.setOnClickListener {
            navControl.navigate(R.id.action_signInFragment_to_eventListFragment)
        }
        binding.createAccount.setOnClickListener {
            navControl.navigate(R.id.action_signInFragment_to_eventListFragment)
        }
    }
}