package com.example.seminar_management_system.data.repository

import com.example.seminar_management_system.data.remote.BooksApi
import com.example.seminar_management_system.domain.model.bookmodel.BookModel
import com.example.seminar_management_system.domain.model.loginmodel.LoginModel
import com.example.seminar_management_system.domain.model.loginmodel.LoginStatus
import com.example.seminar_management_system.presentation.screens.registeredUserInterface.homeScreen.bookstate.Books
import retrofit2.Response
import javax.inject.Inject

class BooksRepository @Inject constructor(private val api: BooksApi){
    suspend fun userLogin(loginModel: LoginModel): Response<LoginStatus> {
        return api.logInUser(loginModel)
    }
    suspend fun userSignIn(signInModel: LoginModel): Response<LoginStatus> {
        return api.signInUser(signInModel)
    }
    suspend fun getBooks():List<Books>{
        return api.getBooks()
    }
}