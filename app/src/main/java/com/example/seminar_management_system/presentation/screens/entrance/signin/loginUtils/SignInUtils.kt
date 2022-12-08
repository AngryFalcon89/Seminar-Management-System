package com.example.seminar_management_system.presentation.screens.entrance.signin.loginUtils

class SignInUtils {

    fun loginFormatValidation(email: String, password: String): Int {

        if (email.trim().isNotEmpty()) {

            if (email.length > 5) {

                if (email.contains("@")) {

                    return if (password.trim().isNotEmpty()) {

                        1

                    } else {


                        5

                    }


                } else {

                    return 4

                }


            } else {

                return 3

            }

        } else {

            return 2

        }
    }
}