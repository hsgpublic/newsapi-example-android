package hsgpublic.example.newsapi.data.remote

import hsgpublic.example.newsapi.data.remote.endpoint.TopHeadlinesEndpoint
import hsgpublic.example.newsapi.data.remote.helper.HttpRequester
import hsgpublic.example.newsapi.data.remote.helper.TopHeadlinesRequester
import hsgpublic.example.newsapi.data.remote.model.TopHeadlinesResponseModel

class TopHeadlinesRemoteDataSource(
    private val httpRequester: HttpRequester = TopHeadlinesRequester()
) {
    suspend fun getTopHeadlines(country: String): TopHeadlinesResponseModel {
        val endpoint = TopHeadlinesEndpoint(country = country)
        return httpRequester.request(
            endpoint = endpoint
        ) as TopHeadlinesResponseModel
    }
}