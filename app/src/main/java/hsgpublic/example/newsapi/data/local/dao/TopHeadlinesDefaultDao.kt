package hsgpublic.example.newsapi.data.local.dao

import android.content.Context
import hsgpublic.example.newsapi.data.local.entity.HeadlineEntity

class TopHeadlinesDefaultDao(
    private val context: Context
): TopHeadlinesDao {
    private val database = RoomHelper.getDatabase(context)
    private val roomDao: TopHeadlinesRoomDao = database.topHeadlinesDao()

    override suspend fun readAll(): List<HeadlineEntity> {
        val roomEntities = roomDao.getAll()
        return roomEntities.map { it.asHeadlineEntity() }
    }

    override suspend fun read(compositeKeys: List<String>): List<HeadlineEntity> {
        val roomEntities = roomDao.getAll()
        return roomEntities.map { it.asHeadlineEntity() }
    }

    override suspend fun upsert(entities: List<HeadlineEntity>) {
        val roomEntities = entities.map { it.asRoomEntity() }
        roomDao.upsert(roomEntities)
    }

    override suspend fun delete(compositeKeys: List<String>) {
        roomDao.delete(compositeKeys)
    }
}