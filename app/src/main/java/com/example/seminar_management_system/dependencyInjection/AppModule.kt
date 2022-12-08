package com.example.seminar_management_system.dependencyInjection

import com.example.seminar_management_system.data.remote.BooksApi
import com.example.seminar_management_system.data.repository.BooksRepository
import com.example.seminar_management_system.data.repository.BooksRepositoryInterface
import com.example.seminar_management_system.util.constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun wordApiRepository(api: BooksApi) = BooksRepository(api) as BooksRepositoryInterface

    @Singleton
    @Provides
    fun injectBackendRetrofitApi() : BooksApi {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(BooksApi::class.java)
    }

    @ExperimentalCoroutinesApi
    @Provides
    fun providesloginRepository(
        apiService: BooksApi
    ): BooksRepository {
        return BooksRepository(apiService)
    }
}