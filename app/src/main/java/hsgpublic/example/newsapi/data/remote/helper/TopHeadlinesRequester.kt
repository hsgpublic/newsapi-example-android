package hsgpublic.example.newsapi.data.remote.helper

import hsgpublic.example.newsapi.data.remote.endpoint.Endpoint
import hsgpublic.example.newsapi.data.remote.endpoint.TopHeadlinesEndpoint

class TopHeadlinesRequester: HttpRequester {
    private val service = RetrofitHelper.getService(
        TopHeadlinesService::class.java
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