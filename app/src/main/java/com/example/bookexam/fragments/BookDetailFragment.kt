package com.example.bookexam.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.bookexam.R
import com.example.bookexam.databinding.FragmentBookDetailBinding
import com.example.bookexam.models.Book
import com.example.bookexam.vm.BookDBViewModel

class BookDetailFragment : Fragment() {

    private lateinit var binding: FragmentBookDetailBinding
    private val viewModel: BookDBViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_detail, container, false)
        binding = FragmentBookDetailBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bundle = requireArguments()
        if (bundle != null) {
            val id = bundle.getInt("id")
            val title = bundle.getString("title")
            val author = bundle.getString("author")
            val isbn = bundle.getString("isbn")
            val year = bundle.getInt("year")

            binding.editTitle.setText(title)
            binding.editAuthor.setText(author)
            binding.editISBN.setText(isbn)
            binding.editYear.setText(year.toString())

            binding.updateButton.setOnClickListener {
                var book = validateInput(id)
                if (book != null) {
                    Toast.makeText(context, "Book Updated", Toast.LENGTH_SHORT).show()
                    viewModel.bookUpdateResult(book)
                    Navigation.findNavController(view).popBackStack()
                } else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }

            }

            viewModel.bookUpdateResult.observe(viewLifecycleOwner) {
                if (it != null) {
                    Toast.makeText(context, "Book Updated", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Book Update Failed", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Navigation.findNavController(view).popBackStack()
        }

    }

    private fun validateInput(bookId: Int): Book? {
        val title = binding.editTitle.text.toString()
        val author = binding.editAuthor.text.toString()
        val isbn = binding.editISBN.text.toString()
        val year = binding.editYear.text.toString()
        // year必须是数字检测
        val isNumber = year.toIntOrNull()
        val result = !(title.isEmpty() || author.isEmpty() || isbn.isEmpty() || isNumber == null)
        if (result) {
            return Book(id = bookId, title = title, author = author, isbn = isbn, year = isNumber!!)
        }
        return null
    }
}
