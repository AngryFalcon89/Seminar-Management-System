package com.example.seminar_management_system.presentation.screens.entrance.signin.viewModel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.seminar_management_system.data.repository.BooksRepository
import com.example.seminar_management_system.domain.model.loginmodel.LoginModel
import com.example.seminar_management_system.presentation.screens.entrance.login.state.LoginState
import com.example.seminar_management_system.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import retrofit2.HttpException
import java.io.IOException

@ExperimentalCoroutinesApi
@HiltViewModel
class SigninViewModel @Inject constructor(
    application: Application,
    private val repository: BooksRepository
) : AndroidViewModel(application) {

    private val _state = mutableStateOf(LoginState("",false,""))
    val state: State<LoginState> = _state


    fun UserSignIn(loginModel: LoginModel) {
        if (loginModel.username.trim().isEmpty() || loginModel.password.trim().isEmpty()) {
            toast(getApplication(), "Values can't be empty!")
            return
        } else if (loginModel.username.length <= 3 || loginModel.password.length <= 8) {
            toast(
                getApplication(),
                "Username should be greater than or equal to 3 and password should be greater than or equal to 8"
            )
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            getSignin(loginModel)
        }
    }

    suspend fun getSignin(SignInModel: LoginModel) {
        try {
            if (hasInternetConnection<MyApplication>()) {
                val response = repository.userSignIn(SignInModel)
                Log.e("LOG:::",response.raw().toString())
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
                    toast(getApplication(), response.raw().code().toString())

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
