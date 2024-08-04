package hsgpublic.example.newsapi.data.local

import hsgpublic.example.newsapi.data.local.entity.HeadlineEntity
import kotlinx.coroutines.flow.Flow

interface TopHeadlinesLocalDataSource {
    fun readHeadlines(): Flow<List<HeadlineEntity>>
    fun upsertHeadlines(entities: List<HeadlineEntity>): Flow<Unit>
    fun deleteHeadlines(compositionKeys: List<String>): Flow<Unit>
}