package com.example.guidehelper.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.guidehelper.R
import com.example.guidehelper.data.Event
import com.example.guidehelper.data.EventDatabase
import com.example.guidehelper.databinding.FragmentEventListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        // БАЗА ДАННЫХ
        /*CoroutineScope(Dispatchers.IO).launch {
            val db = Room.databaseBuilder(
                context = requireContext().applicationContext,
                EventDatabase::class.java, "event-database"
            ).build()
            val eventDao = db.eventDao()
            eventDao.insertAll(Event(name="Игра в шахматы", date = "08.12.2024", place = "Вернадка 78", info = "Сегодня будем играть в шахматы!"))
            val events: List<Event> = eventDao.getAll()

            withContext(Dispatchers.Main) {
                val eventAdapter = EventAdapter(events.toMutableList())
                binding!!.eventList.adapter = eventAdapter
            }
        }*/
        return view
    }
}