package com.wcsm.potterhubmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.wcsm.potterhubmvvm.databinding.SpellItemBinding
import com.wcsm.potterhubmvvm.model.Spell

class SpellAdapter : Adapter<SpellAdapter.SpellViewHolder>() {

    private var spells = mutableListOf<Spell>()

    fun updateSpellsList(spells: MutableList<Spell>) {
        this.spells = spells
        notifyDataSetChanged()
    }

    inner class SpellViewHolder(private val binding: SpellItemBinding) : ViewHolder(binding.root) {
        fun bind(spell: Spell) {
            with(binding) {
                textSpell.text = spell.spell
                textUse.text = spell.use
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SpellItemBinding.inflate(layoutInflater, parent, false)
        return SpellViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpellViewHolder, position: Int) {
        val spell = spells[position]
        holder.bind(spell)
    }

    override fun getItemCount(): Int {
        return spells.size
    }
}