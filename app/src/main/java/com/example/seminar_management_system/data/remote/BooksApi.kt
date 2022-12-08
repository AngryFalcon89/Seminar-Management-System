package com.example.seminar_management_system.data.remote

import com.example.seminar_management_system.domain.model.bookmodel.BookModel
import com.example.seminar_management_system.domain.model.loginmodel.LoginModel
import com.example.seminar_management_system.domain.model.loginmodel.LoginStatus
import com.example.seminar_management_system.presentation.screens.registeredUserInterface.homeScreen.bookstate.Books
import retrofit2.Response
import retrofit2.http.*

interface BooksApi {
    @POST("/login")
    suspend fun logInUser(@Body loginModel: LoginModel): Response<LoginStatus>

    @POST("/register")
    suspend fun signInUser(signInModel: LoginModel): Response<LoginStatus>

    @GET("/books")
    suspend fun getBooks(): List<Books>

}