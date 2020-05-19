package com.shopping.baseproject.data.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.shopping.baseproject.data.models.Store
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ShoppingAPI {

    @GET("/api/aisles")
    suspend fun getAllAisles(): List<Aisle>

    @POST("/api/store_mapping")
    suspend fun getShopMap(@Body id: Int): List<StoreCell>

    @GET("/api/stores")
    suspend fun getStores(): List<Store>

    @POST("/api/path")
    suspend fun getPath(@Query("storeId") storeId: Int, @Query("startId") startId: Int, @Body points: List<Aisle>): List<StoreCell>

    // The Retrofit class generates an implementation for the interface
    companion object {
        fun create(): ShoppingAPI {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://grocery-store-pc.herokuapp.com/")
                .client(client)
                .build()
            return retrofit.create(ShoppingAPI::class.java)
        }
    }
}
