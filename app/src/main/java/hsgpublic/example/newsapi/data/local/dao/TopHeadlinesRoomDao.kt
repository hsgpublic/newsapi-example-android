package hsgpublic.example.newsapi.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import hsgpublic.example.newsapi.data.local.entity.HeadlineRoomEntity

@Dao
interface TopHeadlinesRoomDao {
    @Query("SELECT * FROM HeadlineRoomEntity")
    suspend fun getAll(): List<HeadlineRoomEntity>

    @Query("SELECT * FROM HeadlineRoomEntity WHERE compositeKey IN (:compositeKeys)")
    suspend fun get(compositeKeys: List<String>): List<HeadlineRoomEntity>

    @Upsert
    suspend fun upsert(headlines: List<HeadlineRoomEntity>)

    @Query("DELETE FROM HeadlineRoomEntity WHERE compositeKey IN (:compositeKeys)")
    suspend fun delete(compositeKeys: List<String>)
}