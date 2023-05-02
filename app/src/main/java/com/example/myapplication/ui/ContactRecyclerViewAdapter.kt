package com.example.myapplication.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.myapplication.R

import com.example.myapplication.ui.placeholder.PlaceholderContent.PlaceholderItem
import com.example.myapplication.databinding.FragmentContactListItemBinding
import com.example.myapplication.model.Contact

class ContactRecyclerViewAdapter(
    private val values: List<Contact>
) : RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentContactListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.rawContactId.text = item.rawContactId.toString()
        holder.name.text = item.name
        holder.starred.text = item.starred.toString()
        holder.hasPhoneNumber.text = item.hasPhoneNumber.toString()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentContactListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val rawContactId: TextView = binding.rawContactId
        val name: TextView = binding.name
        val starred: TextView = binding.starred
        val hasPhoneNumber: TextView = binding.hasPhoneNumber

        override fun toString(): String {
            return super.toString() + " '" + rawContactId.text + "'"
        }
    }

}