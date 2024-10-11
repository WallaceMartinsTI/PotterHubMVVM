package com.wcsm.potterhubmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.wcsm.potterhubmvvm.databinding.HouseItemBinding
import com.wcsm.potterhubmvvm.model.House

class HousesAdapter : Adapter<HousesAdapter.HouseViewHolder>() {

    private var houses = mutableListOf<House>()

    fun updateHousesList(houses: MutableList<House>) {
        this.houses = houses
        notifyDataSetChanged()
    }

    inner class HouseViewHolder(private val binding: HouseItemBinding) : ViewHolder(binding.root) {
        fun bind(house: House) {
            with(binding) {
                textHouseName.text = house.house
                textFounder.text = house.founder
                textAnimal.text = house.animal
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HouseItemBinding.inflate(layoutInflater, parent, false)
        return HouseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HouseViewHolder, position: Int) {
        val house = houses[position]
        holder.bind(house)
    }

    override fun getItemCount(): Int {
        return houses.size
    }
}