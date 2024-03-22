package com.example.bookexam.adapters

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bookexam.databinding.RecyLayoutBinding
import com.example.bookexam.models.Book

class BookAdapter(private val books:List<Book>): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private lateinit var binding: RecyLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        binding = RecyLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.itemView.apply {
            binding.textTitle.text = book.title
            binding.textAuthor.text = book.author
            binding.textISBN.text = book.isbn
            binding.textYear.text = book.year
            this.setOnClickListener {
                AlertDialog.Builder(this.context)
                    .setTitle("Do you want to delete it?")
                    .setMessage("Title: ${book.title}\nAuthor: ${book.author}\nISBN: ${book.isbn}\nYear: ${book.year}")
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()

                    }
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    override fun getItemCount()= books.size


    class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}