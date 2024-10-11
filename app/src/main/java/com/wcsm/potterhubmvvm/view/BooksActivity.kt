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
import com.wcsm.potterhubmvvm.adapter.BookAdapter
import com.wcsm.potterhubmvvm.databinding.ActivityBooksBinding
import com.wcsm.potterhubmvvm.viewmodel.BooksViewModel

class BooksActivity : AppCompatActivity() {

    private val binding by lazy { ActivityBooksBinding.inflate(layoutInflater) }

    private lateinit var bookAdapter: BookAdapter
    private lateinit var booksViewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bookAdapter = BookAdapter()

        booksViewModel = ViewModelProvider(this)[BooksViewModel::class.java]
        booksViewModel.books.observe(this) { books ->
            bookAdapter.updateBooksList(books.toMutableList())
        }

        binding.rvBooks.adapter = bookAdapter
        binding.rvBooks.layoutManager = LinearLayoutManager(this)
        binding.rvBooks.addItemDecoration(
            DividerItemDecoration(this, RecyclerView.VERTICAL)
        )
    }

    override fun onStart() {
        super.onStart()
        booksViewModel.getBooks()
    }
}