package com.example.bookexam

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.databinding.DataBindingUtil
import com.example.bookexam.databinding.CreateLayoutBinding
import com.example.bookexam.models.Book
import com.example.bookexam.ui.theme.BookExamTheme
import com.example.bookexam.vm.BookViewModel

class MainActivity : ComponentActivity() {
    private lateinit var binding: CreateLayoutBinding
    private val viewModel: BookViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.create_layout)

        validateInput()

        binding.createBook.setOnClickListener {
            var book = validateInput()
            if (book != null) {
                Toast.makeText(this, "Book Created", Toast.LENGTH_SHORT).show()
                viewModel.createBook(book)
            }else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }

            startActivity(Intent(this, ListActivity::class.java))
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
        val result =  !(title.isEmpty() || author.isEmpty() || isbn.isEmpty() || year.isEmpty())
        if (result){
            return Book(title, author, isbn, year)
        }
        return null
    }
}
