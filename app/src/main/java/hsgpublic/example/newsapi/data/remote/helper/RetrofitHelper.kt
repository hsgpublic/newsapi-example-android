package hsgpublic.example.newsapi.data.remote.helper

import hsgpublic.example.newsapi.config.ServerConfig
import hsgpublic.example.newsapi.data.remote.endpoint.Endpoint
import hsgpublic.example.newsapi.data.remote.endpoint.TopHeadlinesEndpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper: HttpRequester {
    private val retrofit = Retrofit.Builder()
        .baseUrl(ServerConfig.apiBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(
        RetrofitService::class.java
    )

    override suspend fun request(endpoint: Endpoint): Any {
        when(endpoint) {
            is TopHeadlinesEndpoint -> {
                return service.getTopHeadlines(
                    headerMap = endpoint.headers,
                    queryMap = endpoint.baseQuery + endpoint.query
                )
            }
            else -> {
                throw IllegalArgumentException("Bad endpoint.")
            }
        }
    }
}