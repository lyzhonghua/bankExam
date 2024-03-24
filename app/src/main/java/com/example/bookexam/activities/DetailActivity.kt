package com.example.bookexam.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.bookexam.R
import com.example.bookexam.databinding.DetailLayoutBinding
import com.example.bookexam.models.Book
import com.example.bookexam.vm.BookDBViewModel

class DetailActivity : ComponentActivity() {
    private lateinit var binding: DetailLayoutBinding
    private val viewModel: BookDBViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.detail_layout)
        if (intent.extras != null) {
            val id = intent.extras!!.getInt("id")
            val title = intent.extras!!.getString("title")
            val author = intent.extras!!.getString("author")
            val isbn = intent.extras!!.getString("isbn")
            val year = intent.extras!!.getInt("year")

            binding.editTitle.setText(title)
            binding.editAuthor.setText(author)
            binding.editISBN.setText(isbn)
            binding.editYear.setText(year.toString())

            binding.updateButton.setOnClickListener {
                var book = validateInput(id)
                if (book != null) {
                    Toast.makeText(this, "Book Updated", Toast.LENGTH_SHORT).show()
                    viewModel.bookUpdateResult(book)
                }else {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }

                startActivity(Intent(this, ListActivity::class.java))
            }

            viewModel.bookUpdateResult.observe(this) {
                if (it != null) {
                    Toast.makeText(this, "Book Updated", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Book Update Failed", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            finish()
            return
        }

    }

    private fun validateInput(bookId:Int) : Book? {
        val title = binding.editTitle.text.toString()
        val author = binding.editAuthor.text.toString()
        val isbn = binding.editISBN.text.toString()
        val year = binding.editYear.text.toString()
        // year必须是数字检测
        val isNumber = year.toIntOrNull()
        val result =  !(title.isEmpty() || author.isEmpty() || isbn.isEmpty() || isNumber == null)
        if (result){
            return Book(id = bookId, title = title, author = author, isbn = isbn, year = isNumber!!)
        }
        return null
    }
}
