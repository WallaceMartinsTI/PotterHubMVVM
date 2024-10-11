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
import com.wcsm.potterhubmvvm.adapter.CharacterAdapter
import com.wcsm.potterhubmvvm.databinding.ActivityCharacterBinding
import com.wcsm.potterhubmvvm.viewmodel.CharactersViewModel

class CharactersActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCharacterBinding.inflate(layoutInflater) }

    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var charactersViewModel: CharactersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        characterAdapter = CharacterAdapter()

        charactersViewModel = ViewModelProvider(this)[CharactersViewModel::class.java]
        charactersViewModel.characters.observe(this) { character ->
            characterAdapter.updateCharactersList(character.toMutableList())
        }

        binding.rvCharacters.adapter = characterAdapter
        binding.rvCharacters.layoutManager = LinearLayoutManager(this)
        binding.rvCharacters.addItemDecoration(
            DividerItemDecoration(this, RecyclerView.VERTICAL)
        )
    }

    override fun onStart() {
        super.onStart()
        charactersViewModel.getCharacters()
    }
}