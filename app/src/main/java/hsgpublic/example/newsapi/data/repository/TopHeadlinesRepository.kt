package hsgpublic.example.newsapi.data.repository

import hsgpublic.example.newsapi.data.model.HeadlineModel
import hsgpublic.example.newsapi.data.remote.TopHeadlinesRemoteDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TopHeadlinesRepository(
    private val remoteDataSource: TopHeadlinesRemoteDataSource = TopHeadlinesRemoteDataSource(),
    private val refreshIntervalMs: Long = 100
) {
    private var _headlines: List<HeadlineModel> = listOf()
    val headlines: Flow<List<HeadlineModel>> = flow {
        while(true) {
            emit(_headlines)
            delay(refreshIntervalMs)
        }
    }

    fun fetchTopHeadlines(country: String) {
        runBlocking {
            try {
                this.launch {
                    _headlines = remoteDataSource.getTopHeadlines(country)
                        .last()
                        .articles
                        .map { article ->
                            HeadlineModel(
                                title = article.title,
                                publishedAt = article.publishedAt,
                                author = article.author,
                                urlToImage = article.urlToImage,
                                url = article.url
                            )
                        }
                }
            } catch (e: Exception) {
                throw e
            }
        }
    }
}