package com.example.seminar_management_system.presentation.navigation

sealed class Screen(val route : String){
    object Splash: Screen("splash_screen")//will represent our splash screen
    object Login: Screen("log_in")
    object Signup: Screen("sign_in")
    object Home: Screen("home_screen")
    object AddUser: Screen("add_user")
    object IssueBook: Screen("issue_book/{bookId}"){
        fun passBookId(bookId: Int):String {
            return "issue_book/$bookId"
        }
    }
    object Search: Screen("search_screen")
}
