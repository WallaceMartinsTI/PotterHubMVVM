package com.wcsm.potterhubmvvm.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wcsm.potterhubmvvm.R
import com.wcsm.potterhubmvvm.adapter.SpellAdapter
import com.wcsm.potterhubmvvm.databinding.ActivitySpellsBinding
import com.wcsm.potterhubmvvm.viewmodel.SpellsViewModel

class SpellsActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySpellsBinding.inflate(layoutInflater) }

    private lateinit var spellAdapter: SpellAdapter
    private lateinit var spellsViewModel: SpellsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spellAdapter = SpellAdapter()

        spellsViewModel = ViewModelProvider(this)[SpellsViewModel::class.java]
        spellsViewModel.spells.observe(this) { spells ->
            spellAdapter.updateSpellsList(spells.toMutableList())
        }

        binding.rvSpells.adapter = spellAdapter
        binding.rvSpells.layoutManager = LinearLayoutManager(this)
        binding.rvSpells.addItemDecoration(
            DividerItemDecoration(this, RecyclerView.VERTICAL)
        )
    }

    override fun onStart() {
        super.onStart()
        spellsViewModel.getSpells()
    }
}