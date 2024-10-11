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
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.wcsm.potterhubmvvm.R
import com.wcsm.potterhubmvvm.adapter.HousesAdapter
import com.wcsm.potterhubmvvm.databinding.ActivityHousesBinding
import com.wcsm.potterhubmvvm.viewmodel.HousesViewModel

class HousesActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHousesBinding.inflate(layoutInflater) }

    private lateinit var housesAdapter: HousesAdapter
    private lateinit var housesViewModel: HousesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        housesAdapter = HousesAdapter()

        housesViewModel = ViewModelProvider(this)[HousesViewModel::class.java]
        housesViewModel.houses.observe(this) { houses ->
            housesAdapter.updateHousesList(houses.toMutableList())
        }

        binding.rvHouses.adapter = housesAdapter
        binding.rvHouses.layoutManager = LinearLayoutManager(this)
        binding.rvHouses.addItemDecoration(
            DividerItemDecoration(this, RecyclerView.VERTICAL)
        )
    }

    override fun onStart() {
        super.onStart()
        housesViewModel.getHouses()
    }
}