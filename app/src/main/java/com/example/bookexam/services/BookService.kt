package com.example.bookexam.services

import com.example.bookexam.models.Book
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BookService {

    companion object {
        const val BASE_URL = "https://www.google.com"
        private var service: BookService? = null

        fun getApiService(): BookService {
            if (service == null) {
                val httpLogger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

                val client = OkHttpClient.Builder()
                    .addInterceptor(httpLogger)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

                service = retrofit.create(BookService::class.java)
            }
            return service!!
        }
    }

    /**
     * get books from the server
     */
    @GET("books")
    suspend fun getBooks() : String

    /**
     * create a book to server
     * return the id of the book
     */
    @POST("create")
    suspend fun createBook(@Body book: Book): Int

    /**
     * update a book to server
     */
    @POST("update")
    suspend fun updateBook(@Body book: Book):Boolean

    /**
     * delete a book from server
     */
    @DELETE("delete/{id}")
    suspend fun deleteById(@Path("id") id:Int):Boolean

}