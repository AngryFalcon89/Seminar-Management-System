package com.example.seminar_management_system.presentation.screens.registeredUserInterface.homeScreen.bookstate

import com.google.gson.annotations.SerializedName

data class Books(
    var Id: Int?,
    var AccessionNumber: String?,
    var MalAccNo: String?,
    var Author: String?,
    var Title: String?,
    var BookStatus: String?,
    var Edition: String?,
    var Publisher: String?,
    var Catagory1: String?,
    var Catagory2: String?,
    var Catagory3: String?,
    var PublishingYear: String?,
    var Author1: String?,
    var Author2: String?,
    var Author3: String?
)
