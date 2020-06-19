package net.test.newsappvivek.network

import android.util.Log
import net.test.newsappvivek.AppConstant.BASE_URL
import net.test.newsappvivek.model.NewsResponse
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {
    @GET("v2/top-headlines")
   suspend fun getLatestNews(
        @Query("pageSize") pageSize: Int,
        @Query("page") nextPage: Int?,
        @Query("country") country: String,
        @Query("apikey") apiKey: String
    ):NewsResponse

    companion object {
        fun create(): NewsAPIService {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Log.d("API", it) })
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(HttpUrl.parse(BASE_URL)!!)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsAPIService::class.java)
        }
    }
}