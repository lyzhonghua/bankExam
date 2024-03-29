package com.example.bookexam.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookexam.R
import com.example.bookexam.adapters.BookAdapter
import com.example.bookexam.databinding.FragmentBookListBinding
import com.example.bookexam.vm.BookDBViewModel

class BookListFragment : Fragment() {

    private lateinit var binding: FragmentBookListBinding
    private val viewModel: BookDBViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_list, container, false)
        binding = FragmentBookListBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBooksFromDatabase()

        val adapter = BookAdapter(viewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(context).apply { orientation = LinearLayoutManager.VERTICAL }
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        binding.recyclerView.adapter = adapter

        viewModel.booksData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        viewModel.bookDeleteResult.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(context, "Book Deleted", Toast.LENGTH_SHORT).show()
                viewModel.getBooksFromDatabase()
            } else {
                Toast.makeText(context, "Book Deletion Failed", Toast.LENGTH_SHORT).show()
            }
        }

    }

}