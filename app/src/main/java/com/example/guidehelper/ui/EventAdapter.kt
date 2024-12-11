package com.example.guidehelper.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guidehelper.R
import com.example.guidehelper.data.EventItem
import com.example.guidehelper.databinding.EventItemBinding

class EventAdapter(private val eventList:MutableList<EventItem>) :
    RecyclerView.Adapter<EventAdapter.ViewHolder>()  {

    private var listener: EventAdapterClicksInterface? = null
    fun setListener(listener: EventAdapterClicksInterface){
        this.listener = listener
    }
    inner class ViewHolder(val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return eventList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(eventList[position]) {
                binding.nameTextView.text = this.name
                binding.dateTextView.text = this.date
                binding.view.setOnClickListener {
                    listener?.onOpenEventClicked(this)
                }

            }
        }
    }
    interface EventAdapterClicksInterface{
        fun onOpenEventClicked(event: EventItem)
    }
}