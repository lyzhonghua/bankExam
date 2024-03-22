package com.example.bookexam

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookexam.adapters.BookAdapter
import com.example.bookexam.databinding.ListLayoutBinding
import com.example.bookexam.models.Book
import com.example.bookexam.vm.BookViewModel

class ListActivity : ComponentActivity() {
    private lateinit var binding: ListLayoutBinding
    private val viewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.list_layout)

        // fake data
        val books = listOf(
            Book("The Great Gatsby", "F. Scott Fitzgerald", "978-3-16-148410-0", "1925"),
            Book("To Kill a Mockingbird", "Harper Lee", "978-3-16-148410-1", "1960"),
            Book("1984", "George Orwell", "978-3-16-148410-2", "1949"),

        )

        binding.getDataButton.setOnClickListener {
            viewModel.getBooks()
        }

        val adapter = BookAdapter(books)
        binding.recyclerView.layoutManager = LinearLayoutManager(this).apply { orientation = LinearLayoutManager.VERTICAL }
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.recyclerView.adapter = adapter


        viewModel.booksLiveData.observe(this) {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        }

    }

}