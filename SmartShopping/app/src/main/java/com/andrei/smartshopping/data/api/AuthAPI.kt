package com.andrei.smartshopping.data.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.andrei.smartshopping.data.models.UserProfile
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {

    @POST("/api/login")
    suspend fun login(@Body user: UserProfile)

    @POST("/api/register")
    suspend fun register(@Body user: UserProfile)

    // The Retrofit class generates an implementation for the interface
    companion object {
        fun create(): AuthAPI {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://grocery-store-pc.herokuapp.com/")
                .client(client)
                .build()
            return retrofit.create(AuthAPI::class.java)
        }
    }
}
