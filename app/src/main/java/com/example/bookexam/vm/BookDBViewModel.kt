package com.example.bookexam.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookexam.db.BookDatabase
import com.example.bookexam.models.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookDBViewModel : ViewModel() {

    val booksData: MutableLiveData<List<Book>> = MutableLiveData()
    val bookDeleteResult: MutableLiveData<Int> = MutableLiveData()
    val bookCreateResult: MutableLiveData<Int> = MutableLiveData()
    val bookUpdateResult: MutableLiveData<Boolean> = MutableLiveData()



    // create a book to database
    fun createBookToDatabase(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            BookDatabase.getDatabase().bookDao().insert(book)
        }
    }

    // get all books from database
    fun getBooksFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            booksData.postValue(BookDatabase.getDatabase().bookDao().getAll())
        }
    }

    // update a book in database
    fun bookUpdateResult(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            BookDatabase.getDatabase().bookDao().update(book)
        }
    }

    // delete a book from database
    fun delete(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            bookDeleteResult.postValue(BookDatabase.getDatabase().bookDao().delete(book))
        }
    }
}