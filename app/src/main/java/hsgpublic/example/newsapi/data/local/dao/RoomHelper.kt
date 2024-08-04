package hsgpublic.example.newsapi.data.local.dao

import android.content.Context
import androidx.room.Room
import hsgpublic.example.newsapi.config.DatabaseConfig

object RoomHelper {
    private var database: NewsApiRoomDatabase? = null

    fun getDatabase(context: Context): NewsApiRoomDatabase {
        if(database == null) {
            database = Room.databaseBuilder(
                context,
                NewsApiRoomDatabase::class.java,
                DatabaseConfig.DATABASE_NAME
            ).build()
        }
        return database!!
    }
}