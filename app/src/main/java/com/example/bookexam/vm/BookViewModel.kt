package com.example.bookexam.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookexam.models.Book
import com.example.bookexam.services.BookService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {

    val booksLiveData: MutableLiveData<String> = MutableLiveData()
    val bookDeleteResult: MutableLiveData<Boolean> = MutableLiveData()
    val bookCreateResult: MutableLiveData<Int> = MutableLiveData()
    val bookUpdateResult: MutableLiveData<Boolean> = MutableLiveData()


    /**
     * Create a books from server
     */
    fun createBook(book: Book) {
        viewModelScope.launch() {
            val result = try {
                Result.success(BookService.getApiService().createBook(book))
            } catch (e: Exception) {
                Result.failure(e)
            }

            bookCreateResult.value = result.getOrNull()
        }
    }

    /**
     * get books from the server
     * through the coroutine
     */
    fun getBooks() {
        viewModelScope.launch() {
            val result = try {
                Result.success(BookService.getApiService().getBooks())
            } catch (e: Exception) {
                Result.failure(e)
            }
            booksLiveData.value = result.getOrNull()
        }
    }

    /**
     * delete a book from server
     */
    fun deleteById(bookId: Int) {
        viewModelScope.launch {
            val result = try {
                Result.success(BookService.getApiService().deleteById(bookId))
            } catch (e: Exception) {
                Result.failure(e)
            }
            bookDeleteResult.value = result.getOrNull()
        }
    }

    /**
     * update a book from server
     */
    fun updateBook(book: Book) {
        viewModelScope.launch {
            val result = try {
                Result.success(BookService.getApiService().updateBook(book))
            } catch (e: Exception) {
                Result.failure(e)
            }
            bookUpdateResult.value = result.getOrNull()
        }
    }

}