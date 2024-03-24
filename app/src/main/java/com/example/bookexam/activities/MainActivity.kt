package com.example.bookexam.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.bookexam.R
import com.example.bookexam.databinding.CreateLayoutBinding
import com.example.bookexam.models.Book
import com.example.bookexam.vm.BookDBViewModel

class MainActivity : ComponentActivity() {
    private lateinit var binding: CreateLayoutBinding
    private val viewModel: BookDBViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.create_layout)

        binding.createBook.setOnClickListener {
            var book = validateInput()
            if (book != null) {
                Toast.makeText(this, "Book Created", Toast.LENGTH_SHORT).show()
                viewModel.createBookToDatabase(book)

                startActivity(Intent(this, ListActivity::class.java))
            }else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }

        }

        viewModel.bookCreateResult.observe(this) {
            if (it != null) {
                Toast.makeText(this, "Book Created with id: $it", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Book Creation Failed", Toast.LENGTH_SHORT).show()
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
