package com.example.bookexam.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bookexam.models.Book

@Dao
interface BookDao {

    /**
     * Insert a book into the database
     */
    @Insert
    fun insert(book: Book)

    /**
     * Delete a book from the database
     */
    @Delete
    fun delete(book: Book): Int

    /**
     * Update a book in the database
     */
    @Update
    fun update(book: Book)

    /**
     * Get all books from the database
     */
    @Query("SELECT * FROM book")
    fun getAll(): List<Book>

    /**
     * Get a book by id from the database
     */
    @Query("SELECT * FROM book WHERE id = :bookId")
    fun getById(bookId: Int): Book

    /**
     * query by title
     */
    @Query("SELECT * FROM book WHERE title = :title")
    fun getByTitle(title: String): Book
}