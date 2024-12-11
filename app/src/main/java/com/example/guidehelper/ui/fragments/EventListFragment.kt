package com.example.guidehelper.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guidehelper.R
import com.example.guidehelper.data.EventItem
import com.example.guidehelper.databinding.FragmentEventListBinding
import com.example.guidehelper.ui.EventAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EventListFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentEventListBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var eventList: MutableList<EventItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        registerEvents()
    }

    private fun init(view: View) {
        navController = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference().child("events")

        eventList = mutableListOf()
        fetchData()

        binding.eventList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
        }
    }

    private fun fetchData() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                eventList.clear()
                if(snapshot.exists()){
                    for (eventSnapshot in snapshot.children){
                        val eventItem = eventSnapshot.getValue(EventItem::class.java)
                        eventList.add(eventItem!!)
                    }
                }
                val rvAdapter = EventAdapter(eventList)
                binding.eventList.adapter = rvAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun registerEvents() {
        binding.addEventButton.setOnClickListener {
            navController.navigate(R.id.action_register_new_event)
        }
        binding.backButton.setOnClickListener {
            auth.signOut()
            navController.popBackStack()
        }
    }
}