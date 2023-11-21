package com.rangga.resepin.ui.data.retrofit

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthInterceptor(context: Context) : Interceptor {
//    private val sessionManager = TokenDatastore(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // If token has been saved, add it to the request
//        sessionManager.getToken()?.let {
//            requestBuilder.addHeader("Authorization", "Bearer $it")
//        }

        return chain.proceed(requestBuilder.build())
    }
}

object ApiRequest {
    private var apiService: ApiService? = null

    fun getApiService(context: Context): ApiService {
        if (apiService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://resepin-api.vercel.app/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient(context))
                .build()

            apiService = retrofit.create(ApiService::class.java)
        }

        return apiService!!
    }

    private fun okhttpClient(context: Context): OkHttpClient {
        // Logging Interceptor
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .addInterceptor(loggingInterceptor) // Add logging
            .build()
    }


}