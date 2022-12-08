package com.example.seminar_management_system.presentation.screens.registeredUserInterface.homeScreen.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.seminar_management_system.data.repository.BooksRepository
import com.example.seminar_management_system.presentation.screens.registeredUserInterface.homeScreen.bookstate.Books
import com.example.seminar_management_system.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val repository: BooksRepository
) : AndroidViewModel(application) {

    private val _state = mutableStateListOf<Books>()
    val state: List<Books> = _state
    private val _message = MutableLiveData<Event<String>>()

    fun getBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            getLogin()
        }
    }

    private suspend fun getLogin() {
        try {
            if (hasInternetConnection<MyApplication>()) {
                _state.clear()
                _state.addAll(repository.getBooks())
                Log.e("LOG3:::", _state[1496].toString())

            } else {
                _message.postValue(Event("Not Internet Connection"))
                toast(getApplication(), "No Internet Connection.!")
            }
        } catch (e: HttpException) {
            when (e) {

                else -> {
                    toast(getApplication(), "Exception ${e.message}")
                    Log.e("HTTP EXCEPTION:::", e.message.toString())
                }
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    toast(getApplication(), t.message!!)
                    Log.e("IO EXCEPTION:::", t.message.toString())
                }

            }

        }
    }
}