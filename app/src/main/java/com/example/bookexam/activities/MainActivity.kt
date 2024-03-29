package com.example.bookexam.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.bookexam.R
import com.example.bookexam.models.Book
import com.example.bookexam.vm.BookDBViewModel

class MainActivity : AppCompatActivity() {
//    private lateinit var binding: Main
//    private val viewModel: BookDBViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = navHostFragment?.findNavController()
//        val navigationController = Navigation.findNavController(this, R.id.nav_host_fragment)







    }

}
