package hsgpublic.example.newsapi.data.local

import android.content.Context
import hsgpublic.example.newsapi.data.local.dao.TopHeadlinesDao
import hsgpublic.example.newsapi.data.local.dao.TopHeadlinesDefaultDao
import hsgpublic.example.newsapi.data.local.entity.HeadlineEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TopHeadlinesDefaultLocalDataSource(
    private val context: Context,
    private val dao: TopHeadlinesDao = TopHeadlinesDefaultDao(context)
): TopHeadlinesLocalDataSource {
    override fun readHeadlines(): Flow<List<HeadlineEntity>> {
        return flow {
            val entities = dao.readAll()
            emit(entities)
        }
    }

    override fun upsertHeadlines(entities: List<HeadlineEntity>): Flow<Unit> {
        return flow {
            dao.upsert(entities)
            emit(Unit)
        }
    }

    override fun deleteHeadlines(compositionKeys: List<String>): Flow<Unit> {
        return flow {
            dao.delete(compositionKeys)
            emit(Unit)
        }
    }
}