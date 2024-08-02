package hsgpublic.example.newsapi.data.repository

import hsgpublic.example.newsapi.data.model.HeadlineModel
import hsgpublic.example.newsapi.data.remote.TopHeadlinesRemoteDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class TopHeadlinesRepository(
    private val remoteDataSource: TopHeadlinesRemoteDataSource = TopHeadlinesRemoteDataSource(),
    private val refreshIntervalMs: Long = 1000
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
            remoteDataSource.getTopHeadlines(country)
                .map { topHeadlines ->
                    topHeadlines.articles.map { article ->
                        HeadlineModel(
                            title = article.title.orEmpty(),
                            publishedAt = article.publishedAt.orEmpty(),
                            author = article.author.orEmpty(),
                            urlToImage = article.urlToImage.orEmpty(),
                            url = article.url.orEmpty()
                        )
                    }
                }
                .collect { headlines ->
                    _headlines = headlines
                }
        }

    }
}