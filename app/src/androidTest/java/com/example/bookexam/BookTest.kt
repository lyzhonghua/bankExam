package com.example.bookexam

import androidx.room.Dao
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.bookexam.db.BookDao
import com.example.bookexam.db.BookDatabase
import com.example.bookexam.models.Book
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class BookTest {

    private lateinit var bookDao: BookDao
    private lateinit var bookDatabase: BookDatabase

    @Before
    fun createDatabase() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        bookDatabase = Room.databaseBuilder(appContext, BookDatabase::class.java, "exam_database.db")
            .build()
        bookDao = bookDatabase.bookDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        bookDatabase?.close()
    }

    @Test
    fun insertBook() {
        runBlocking {
            bookDao.insert(Book(title = "mockTitle", author = "mockAuthor", year = 1999, isbn = "mockIsbn"))
            val book = bookDao.getByTitle("mockTitle")

            assertThat(book.title, equalTo("mockTitle"))
        }
    }

    @Test
    fun findAllBooks() {
        runBlocking {
            bookDao.getAll().forEach {
                println("Book { bookId = ${it.id}, bookTitle = ${it.title}, year = ${it.year}, isbn = ${it.isbn}")
            }
        }
    }

    @Test
    fun deleteBook() {
        runBlocking {
            bookDao.delete(Book(id = 0, title = "mockTitle", author = "mockAuthor", year = 1999, isbn = "mockIsbn"))
        }
    }

}
