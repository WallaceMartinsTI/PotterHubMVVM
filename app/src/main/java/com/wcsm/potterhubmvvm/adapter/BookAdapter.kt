package com.wcsm.potterhubmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.wcsm.potterhubmvvm.databinding.BookItemBinding
import com.wcsm.potterhubmvvm.model.Book

class BookAdapter : Adapter<BookAdapter.BookViewHolder>() {

    private var books = mutableListOf<Book>()

    fun updateBooksList(books: MutableList<Book>) {
        this.books = books
        notifyDataSetChanged()
    }

    inner class BookViewHolder(private val binding: BookItemBinding) : ViewHolder(binding.root) {
        fun bind(book: Book) {
            with(binding) {
                Picasso.get()
                    .load(book.cover)
                    .into(imageCover)
                textTitle.text = book.title
                textRelease.text = book.releaseDate
                textPages.text = book.pages.toString()
                textDescription.text = book.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BookItemBinding.inflate(layoutInflater, parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int {
        return books.size
    }
}