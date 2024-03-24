package com.example.bookexam

import android.app.Application

class ExamApplication : Application() {

    companion object {
        lateinit var instance: ExamApplication
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}