package com.example.guidehelper.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guidehelper.R
import com.example.guidehelper.data.EventItem

class EventAdapter(private val eventList:MutableList<EventItem>) : RecyclerView.Adapter<EventAdapter.ViewHolder>()  {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView
        val dateTextView: TextView
        init {
            nameTextView = view.findViewById(R.id.name_text_view)
            dateTextView = view.findViewById(R.id.date_text_view)
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.event_item, viewGroup, false)

        return ViewHolder(view)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.nameTextView.text = eventList[position].name
        viewHolder.dateTextView.text = eventList[position].date
    }

    override fun getItemCount() = eventList.size
}