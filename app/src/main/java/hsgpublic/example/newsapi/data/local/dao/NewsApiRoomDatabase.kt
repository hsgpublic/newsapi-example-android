package hsgpublic.example.newsapi.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import hsgpublic.example.newsapi.config.DatabaseConfig
import hsgpublic.example.newsapi.data.local.entity.HeadlineRoomEntity

@Database(
    version = DatabaseConfig.DATABASE_VERSION,
    entities = [HeadlineRoomEntity::class],
    autoMigrations = [

    ]
)
abstract class NewsApiRoomDatabase: RoomDatabase() {
    abstract fun topHeadlinesDao(): TopHeadlinesRoomDao
}