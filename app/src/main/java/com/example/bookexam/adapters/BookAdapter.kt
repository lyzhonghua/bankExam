package com.example.bookexam.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.bookexam.R
import com.example.bookexam.databinding.RecyLayoutBinding
import com.example.bookexam.models.Book
import com.example.bookexam.vm.BookDBViewModel

class BookAdapter(private val viewModel: BookDBViewModel): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private lateinit var binding: RecyLayoutBinding
    private var books:List<Book> = listOf()
    fun setData(books: List<Book>){
        this.books = books
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        binding = RecyLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        binding.book = books[position]
        holder.itemView.apply {
//            binding.textTitle.text = "Title: ${book.title}"
//            binding.textAuthor.text = "Author: ${book.author}"
//            binding.textISBN.text = "ISBN: ${book.isbn}"
//            binding.textYear.text = "Year: ${book.year}"

            binding.updateButton.setOnClickListener {
                Toast.makeText(it.context, "Update button clicked", Toast.LENGTH_SHORT).show()
                val bundle = Bundle()
                bundle.putInt("id", book.id)
                bundle.putString("title", book.title)
                bundle.putString("author", book.author)
                bundle.putString("isbn", book.isbn)
                bundle.putInt("year", book.year)

                Navigation.findNavController(it).navigate(R.id.fragment_nav_3, bundle)
            }

            binding.deleteButton.setOnClickListener {
                Toast.makeText(it.context, "Delete button clicked", Toast.LENGTH_SHORT).show()
                viewModel.delete(book)
            }

        }
    }

    override fun getItemCount()= books.size


    class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}