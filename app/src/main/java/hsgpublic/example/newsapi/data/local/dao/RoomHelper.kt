package hsgpublic.example.newsapi.data.local.dao

import android.content.Context
import androidx.room.Room
import hsgpublic.example.newsapi.config.DatabaseConfig

class RoomHelper(
    private val context: Context
): DatabaseAccessor {
    private val room = Room.databaseBuilder(
        context,
        NewsApiRoomDatabase::class.java,
        DatabaseConfig.DatabaseName
    ).build()

    override suspend fun <T> read(
        accessorType: DatabaseAccessorType,
        entityType: Class<T>,
        query: String
    ): List<T> {
        return listOf()
    }

    override suspend fun <T> upsert(
        accessorType: DatabaseAccessorType,
        entities: List<T>
    ) {

    }

    override suspend fun <T> delete(
        accessorType: DatabaseAccessorType,
        entityType: Class<T>,
        query: String
    ) {

    }
}