package com.example.seminar_management_system.domain.model.bookmodel

import com.google.gson.annotations.SerializedName

data class BookModel(
    @SerializedName("Id") var Id: Int?,
    @SerializedName("Accession_Number") var AccessionNumber: String?,
    @SerializedName("Mal_Acc_no") var MalAccNo: String?,
    @SerializedName("Author") var Author: String?,
    @SerializedName("Title") var Title: String?,
    @SerializedName("Book_Status") var BookStatus: String?,
    @SerializedName("Edition") var Edition: String?,
    @SerializedName("Publisher") var Publisher: String?,
    @SerializedName("Catagory_1") var Catagory1: String?,
    @SerializedName("Catagory_2") var Catagory2: String?,
    @SerializedName("Catagory_3") var Catagory3: String?,
    @SerializedName("Publishing_year") var PublishingYear: String?,
    @SerializedName("Author_1") var Author1: String?,
    @SerializedName("Author_2") var Author2: String?,
    @SerializedName("Author_3") var Author3: String?
)