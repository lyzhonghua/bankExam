package com.example.bookexam.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bookexam.ExamApplication
import com.example.bookexam.models.Book

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE:  BookDatabase? = null
        fun getDatabase(): BookDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    ExamApplication.instance,
                    BookDatabase::class.java,
                    "exam_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}