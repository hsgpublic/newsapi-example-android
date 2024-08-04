package hsgpublic.example.newsapi.data.repository

import android.content.Context
import hsgpublic.example.newsapi.data.local.TopHeadlinesDefaultLocalDataSource
import hsgpublic.example.newsapi.data.local.TopHeadlinesLocalDataSource
import hsgpublic.example.newsapi.data.model.HeadlineModel
import hsgpublic.example.newsapi.data.remote.TopHeadlinesDefaultRemoteDataSource
import hsgpublic.example.newsapi.data.remote.TopHeadlinesRemoteDataSource
import hsgpublic.example.newsapi.data.remote.model.TopHeadlinesResponseModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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
        runBlocking {
            localDataSource.readHeadlines()
                .map { entities ->
                    entities.map { it.asHeadlineModel() }
                }
                .collect {
                    _headlines = it
                }
        }
    }

    private fun fetchRemoteHeadlines(country: String) {
        runBlocking {
            remoteDataSource.getTopHeadlines(country)
                .collect {
                    saveRemoteHeadlines(it)
                }
        }
    }

    private fun saveRemoteHeadlines(topHeadlines: TopHeadlinesResponseModel) {
        runBlocking {
            val entities = topHeadlines.articles.map { it.asHeadlineEntity() }
            localDataSource.upsertHeadlines(entities)
                .collect {
                    fetchLocalHeadlines()
                }
        }
    }

    override fun markVisited(index: Int) {
        runBlocking {
            if(_headlines.size <= index) {
                return@runBlocking
            }

            _headlines[index].articleVisited = true
            val entity = _headlines[index].asHeadlineEntity()
            localDataSource.upsertHeadlines(listOf(entity))
        }
    }
}