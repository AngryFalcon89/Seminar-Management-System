//package com.example.seminar_management_system.domain.use_case.get_login
//
//import android.util.Log
//import com.example.seminar_management_system.data.repository.BooksRepository
//import com.example.seminar_management_system.domain.model.loginmodel.LoginModel
//import com.example.seminar_management_system.util.Resource
//import com.example.seminar_management_system.util.internetCheck
//import kotlinx.coroutines.coroutineScope
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import retrofit2.HttpException
//import java.io.IOException
//import javax.inject.Inject
//
//class LoginUseCase @Inject constructor(
//
//    private val repository: BooksRepository
//
//) {
//
//    operator fun invoke(username: String, password: String): Flow<Resource<LoginModel>> = flow {
//
//        try {
//            val loginModel = LoginModel(username,password)
//
//            emit(Resource.Loading())
//
//            val process = repository.userLogin(loginModel)
//
//            Log.e("LOG :::", process.data)
//            Log.e("LOG :::", process.success)
////            coroutineScope {
////
////                emit(Resource.Success(process))
////
////            }
//
//
//        } catch (e: HttpException) {
//
//            emit(Resource.Error(e.localizedMessage ?: "an unexpected error!"))
//            Log.e("LOG :::", e.localizedMessage)
//
//        } catch (e: IOException) {
//
//            if (!internetCheck()) {
//
//                emit(Resource.Internet("Check your internet connectivity!"))
//                Log.e("LOG :::", e.localizedMessage)
//
//            }
//        }
//    }
//}