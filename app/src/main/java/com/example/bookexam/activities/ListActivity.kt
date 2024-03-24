package com.example.bookexam.activities

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookexam.R
import com.example.bookexam.adapters.BookAdapter
import com.example.bookexam.databinding.ListLayoutBinding
import com.example.bookexam.models.Book
import com.example.bookexam.vm.BookDBViewModel

class ListActivity : ComponentActivity() {
    private lateinit var binding: ListLayoutBinding
    private val viewModel: BookDBViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.list_layout)

        viewModel.getBooksFromDatabase()

        val adapter = BookAdapter(viewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(this).apply { orientation = LinearLayoutManager.VERTICAL }
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.recyclerView.adapter = adapter

        viewModel.booksData.observe(this) {
            adapter.setData(it)
        }

        viewModel.bookDeleteResult.observe(this) {
            if (it != null) {
                Toast.makeText(this, "Book Deleted", Toast.LENGTH_SHORT).show()
                viewModel.getBooksFromDatabase()
            } else {
                Toast.makeText(this, "Book Deletion Failed", Toast.LENGTH_SHORT).show()
            }
        }

    }

}