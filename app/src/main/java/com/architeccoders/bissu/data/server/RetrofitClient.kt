package com.architeccoders.bissu.data.server

import com.architeccoders.bissu.data.server.login.LoginServices
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
}