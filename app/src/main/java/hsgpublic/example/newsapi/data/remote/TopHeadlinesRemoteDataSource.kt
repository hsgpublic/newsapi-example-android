package hsgpublic.example.newsapi.data.remote

import hsgpublic.example.newsapi.data.remote.endpoint.TopHeadlinesEndpoint
import hsgpublic.example.newsapi.data.remote.helper.HttpRequester
import hsgpublic.example.newsapi.data.remote.helper.TopHeadlinesRequester
import hsgpublic.example.newsapi.data.remote.helper.TopHeadlinesService
import hsgpublic.example.newsapi.data.remote.model.TopHeadlinesResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TopHeadlinesRemoteDataSource(
    private val httpRequester: HttpRequester = TopHeadlinesRequester()
) {
    fun getTopHeadlines(country: String): Flow<TopHeadlinesResponseModel> {
        return flow {
            val endpoint = TopHeadlinesEndpoint(country = country)
            val topHeadlines = httpRequester.request(endpoint) as TopHeadlinesResponseModel
            emit(topHeadlines)
        }
    }
}