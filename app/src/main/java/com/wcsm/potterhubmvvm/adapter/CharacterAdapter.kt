package com.wcsm.potterhubmvvm.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.wcsm.potterhubmvvm.databinding.CharacterItemBinding
import com.wcsm.potterhubmvvm.model.Character

class CharacterAdapter : Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var characters = mutableListOf<Character>()

    fun updateCharactersList(characters: MutableList<Character>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(
        private val binding: CharacterItemBinding
    ) : ViewHolder(binding.root) {
        fun bind(character: Character) {
            Log.i("#-# TESTE #-#", "character: $character")
            with(binding) {
                Picasso.get()
                    .load(character.image)
                    .resize(500, 500)
                    .centerCrop()
                    .into(imageProfile)
                textNickname.text = character.nickname
                textFullname.text = character.fullName
                textHouse.text = character.hogwartsHouse
                textInterpreted.text = character.interpretedBy
                if(character.children.isNotEmpty()) {
                    var childrens = ""
                    character.children.forEach { child ->
                        childrens += "- $child\n"
                    }
                    textChildren.text = childrens
                } else {
                    binding.textChildrenLabel.visibility = View.GONE
                    binding.textChildren.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CharacterItemBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}