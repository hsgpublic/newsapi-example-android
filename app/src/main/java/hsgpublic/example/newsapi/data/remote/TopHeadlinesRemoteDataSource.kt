package hsgpublic.example.newsapi.data.remote

import hsgpublic.example.newsapi.data.remote.model.TopHeadlinesResponseModel
import kotlinx.coroutines.flow.Flow

interface TopHeadlinesRemoteDataSource {
    fun getTopHeadlines(country: String): Flow<TopHeadlinesResponseModel>
}