package hsgpublic.example.newsapi.data.repository

import hsgpublic.example.newsapi.data.model.HeadlineModel
import kotlinx.coroutines.flow.Flow

interface TopHeadlinesRepository {
    val headlines: Flow<List<HeadlineModel>>
    fun fetchTopHeadlines(country: String)
    fun markVisited(index: Int)
}