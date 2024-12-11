package com.example.guidehelper.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.guidehelper.R
import com.example.guidehelper.data.EventItem
import com.example.guidehelper.databinding.FragmentEventListBinding
import com.example.guidehelper.databinding.FragmentRegisterEventBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterEventFragment : Fragment() {

    private lateinit var  navController: NavController
    private lateinit var binding: FragmentRegisterEventBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterEventBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        registerEvents()

    }

    private fun init(view: View) {
        navController = Navigation.findNavController(view)
        databaseReference = FirebaseDatabase.getInstance().getReference().child("events")
    }

    private fun registerEvents() {
        binding.backButton.setOnClickListener {
            navController.popBackStack()
        }
        binding.registerEventButton.setOnClickListener {
            val name = binding.eventNameText.text.toString()
            val date = binding.eventDateText.text.toString()
            val place = binding.eventPlaceText.text.toString()
            val info = binding.eventInfoText.text.toString()

            if(name.isNotEmpty() && date.isNotEmpty() && place.isNotEmpty() && info.isNotEmpty()) {
                val event = EventItem(name, date, place, info)

                databaseReference.push().setValue(event).addOnCompleteListener(){
                    Toast.makeText(context, getString(R.string.event_created), Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                } .addOnFailureListener {
                    Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, getString(R.string.error_fill_text), Toast.LENGTH_SHORT).show()
            }
        }
    }
}