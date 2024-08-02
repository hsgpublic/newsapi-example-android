package hsgpublic.example.newsapi.data.remote.helper

import hsgpublic.example.newsapi.config.ServerConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val retrofit = Retrofit.Builder()
        .baseUrl(ServerConfig.apiBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> getService(type: Class<T>): T {
        return retrofit.create(type)
    }
}