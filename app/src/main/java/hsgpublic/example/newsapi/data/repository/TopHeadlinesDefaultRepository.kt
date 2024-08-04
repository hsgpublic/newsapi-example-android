package hsgpublic.example.newsapi.data.repository

import android.content.Context
import hsgpublic.example.newsapi.data.local.TopHeadlinesDefaultLocalDataSource
import hsgpublic.example.newsapi.data.local.TopHeadlinesLocalDataSource
import hsgpublic.example.newsapi.data.model.HeadlineModel
import hsgpublic.example.newsapi.data.remote.TopHeadlinesDefaultRemoteDataSource
import hsgpublic.example.newsapi.data.remote.TopHeadlinesRemoteDataSource
import hsgpublic.example.newsapi.data.remote.model.TopHeadlinesResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TopHeadlinesDefaultRepository(
    private val context: Context,
    private val localDataSource: TopHeadlinesDefaultLocalDataSource =
        TopHeadlinesDefaultLocalDataSource(context),
    private val remoteDataSource: TopHeadlinesRemoteDataSource =
        TopHeadlinesDefaultRemoteDataSource(),
    private val refreshIntervalMs: Long = 500
): TopHeadlinesRepository {
    private var _headlines: List<HeadlineModel> = listOf()
    override val headlines: Flow<List<HeadlineModel>> = flow {
        while(true) {
            emit(_headlines)
            delay(refreshIntervalMs)
        }
    }

    override fun fetchTopHeadlines(country: String) {
        fetchLocalHeadlines()
        fetchRemoteHeadlines(country)
    }

    private fun fetchLocalHeadlines() {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.readHeadlines()
                .catch {
                    it.printStackTrace()
                }
                .map { entities ->
                    entities.map { it.asHeadlineModel() }
                }
                .collect {
                    _headlines = it
                }
        }
    }

    private fun fetchRemoteHeadlines(country: String) {
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTopHeadlines(country)
                .catch {
                    it.printStackTrace()
                }
                .collect {
                    saveRemoteHeadlines(it)
                }
        }
    }

    private fun saveRemoteHeadlines(topHeadlines: TopHeadlinesResponseModel) {
        CoroutineScope(Dispatchers.IO).launch {
            val entities = topHeadlines.articles.map { article ->
                val entity = article.asHeadlineEntity()
                entity.articleVisited = _headlines.firstOrNull { headline ->
                    headline.publishedAt == entity.publishedAt
                            && headline.author == entity.author
                }?.articleVisited ?: false
                entity
            }
            localDataSource.upsertHeadlines(entities)
                .catch {
                    it.printStackTrace()
                }
                .collect {
                    fetchLocalHeadlines()
                }
        }
    }

    override fun markVisited(index: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            if(_headlines.size <= index) {
                return@launch
            }

            _headlines[index].articleVisited = true
            val entity = _headlines[index].asHeadlineEntity()
            localDataSource.upsertHeadlines(listOf(entity))
                .catch {
                    it.printStackTrace()
                }
                .collect {

                }
        }
    }
}