package com.example.seminar_management_system.presentation.screens.entrance.splash.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.seminar_management_system.data.repository.BooksRepository
import com.example.seminar_management_system.domain.model.loginmodel.LoginModel
import com.example.seminar_management_system.presentation.screens.entrance.login.state.LoginState
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
class SplashViewModel @Inject constructor(
    application: Application,
    private val repository: BooksRepository
):AndroidViewModel(application){

    private val _state = mutableStateOf(LoginState("",false,""))
    val state: State<LoginState> = _state

    suspend fun getLogin(LoginModel: LoginModel) {
        try {
            if (hasInternetConnection<MyApplication>()) {
                val response = repository.userLogin(LoginModel)

                if (response.body()?.success == true) {
                    _state.value = LoginState(
                        response.body()?.data.toString(),
                        response.body()?.success,
                        response.body()?.error_code.toString()
                    )
                } else {
                    _state.value = LoginState(
                        "",
                        false,
                        response.raw().code().toString()
                    )
                    Log.e("LOG2:::", response.raw().code().toString())
                    toast(getApplication(), "No User found!, login to continue")

                }
                Log.e("LOG3:::", response.toString())

            } else {
                toast(getApplication(), "No Internet Connection.!")
            }
        } catch (e: HttpException) {
            when (e) {
                is IOException -> {
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