package com.example.bookexam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bookexam.services.BookService
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class NetInstrumentTest {


    // 定义一个 ViewModel 的接口
    interface MyViewModelInterface {
        fun getData(): LiveData<String>
    }

    @Test
    fun testing() {
        // viewModel
        val mockViewModel = mock(MyViewModelInterface::class.java)

        // data
        val mockData = MutableLiveData<String>()
        mockData.postValue("mock data")

        // action
        `when`(mockViewModel.getData()).thenReturn(mockData)

        // test
        val data = mockViewModel.getData().value
        assertEquals(data, "mock data")
    }

}