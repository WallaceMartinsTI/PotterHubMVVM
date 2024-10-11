package com.wcsm.potterhubmvvm.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wcsm.potterhubmvvm.R
import com.wcsm.potterhubmvvm.api.RetrofitService
import com.wcsm.potterhubmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {
            cardBooks.setOnClickListener {
                openScreen(BooksActivity())
            }
            cardCharacters.setOnClickListener {
                openScreen(CharactersActivity())
            }
            cardHouses.setOnClickListener {
                openScreen(HousesActivity())
            }
            cardSpells.setOnClickListener {
                openScreen(SpellsActivity())
            }
        }
    }

    private fun openScreen(activity: AppCompatActivity) {
        startActivity(Intent(this, activity::class.java))
    }
}