package hsgpublic.example.newsapi.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import hsgpublic.example.newsapi.config.DatabaseConfig
import hsgpublic.example.newsapi.data.local.entity.HeadlineEntity

@Database(
    version = DatabaseConfig.DatabaseVersion,
    entities = [HeadlineEntity::class],
    autoMigrations = [

    ]
)
abstract class NewsApiRoomDatabase: RoomDatabase() {
    abstract fun topHeadlinesDao(): TopHeadlinesRoomDao
}