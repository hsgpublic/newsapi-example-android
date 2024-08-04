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

    @Upsert
    suspend fun upsert(headlines: List<HeadlineRoomEntity>)

    @Delete
    suspend fun delete(headlines: List<HeadlineRoomEntity>)
}