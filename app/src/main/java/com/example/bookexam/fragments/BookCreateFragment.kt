package com.example.bookexam.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.bookexam.R
import com.example.bookexam.databinding.FragmentBookCreateBinding
import com.example.bookexam.models.Book
import com.example.bookexam.vm.BookDBViewModel

class BookCreateFragment : Fragment() {

    private lateinit var binding: FragmentBookCreateBinding
    private val viewModel: BookDBViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view  =  inflater.inflate(R.layout.fragment_book_create, container, false)
        binding = FragmentBookCreateBinding.bind(view)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createBook.setOnClickListener {
            var book = validateInput()
            if (book != null) {
                Toast.makeText(context, "Book Created", Toast.LENGTH_SHORT).show()
                viewModel.createBookToDatabase(book)

                Navigation.findNavController(view).navigate(R.id.fragment_nav_2)
            }else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun validateInput() : Book? {
        val title = binding.editTitle.text.toString()
        val author = binding.editAuthor.text.toString()
        val isbn = binding.editISBN.text.toString()
        val year = binding.editYear.text.toString()
        // year must be a number
        val isNumber = year.toIntOrNull()
        val result =  !(title.isEmpty() || author.isEmpty() || isbn.isEmpty() || isNumber == null)
        if (result){
            return Book(title = title, author = author, isbn = isbn, year = isNumber!!)
        }
        return null
    }

}