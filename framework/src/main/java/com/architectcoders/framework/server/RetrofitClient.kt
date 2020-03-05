package com.architectcoders.framework.server

import com.architectcoders.framework.server.book.BookServices
import com.architectcoders.framework.server.category.CategoryService
import com.architectcoders.framework.server.login.LoginServices
import com.architectcoders.framework.server.observation.ObservationServices
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Anibal Cortez on 2020-01-19.
 */
class RetrofitClient {

    var BASE_URL = "https://bissu.herokuapp.com"

    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    val loginService: LoginServices = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create<LoginServices>(LoginServices::class.java)
        }

    val bookService : BookServices = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create<BookServices>(BookServices::class.java)
        }

    val categoryService : CategoryService =  Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create<CategoryService>(CategoryService::class.java)
        }

    val observationService : ObservationServices = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create<ObservationServices>(ObservationServices::class.java)
        }
}