package hsgpublic.example.newsapi.data.remote.helper

import hsgpublic.example.newsapi.data.remote.model.TopHeadlinesResponseModel
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap

interface TopHeadlinesService {
    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @HeaderMap headerMap: Map<String, String>,
        @QueryMap queryMap: Map<String, String>
    ): TopHeadlinesResponseModel
}