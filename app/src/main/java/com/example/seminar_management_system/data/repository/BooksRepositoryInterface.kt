package com.example.seminar_management_system.data.repository

import com.example.seminar_management_system.domain.model.loginmodel.LoginModel
import com.example.seminar_management_system.domain.model.loginmodel.LoginStatus
import com.example.seminar_management_system.presentation.screens.registeredUserInterface.homeScreen.bookstate.Books
import retrofit2.Response

interface BooksRepositoryInterface {
    suspend fun userLogin(username:String, password:String) : LoginModel

    suspend fun userSignIn(signInModel: LoginModel): Response<LoginStatus>

    suspend fun getBooks():List<Books>
}