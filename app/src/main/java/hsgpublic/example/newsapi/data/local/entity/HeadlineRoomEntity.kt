package hsgpublic.example.newsapi.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HeadlineRoomEntity(
    @PrimaryKey val compositeKey: String,
    val title: String,
    @ColumnInfo(name = "published_at") val publishedAt: String,
    val author: String,
    @ColumnInfo(name = "url_to_image") val urlToImage: String,
    val url: String,
    @ColumnInfo(name = "article_visited") var articleVisited: Boolean = false
) {
    companion object {
        fun makeCompositeKey(publishedAt: String, author: String): String {
            return "$publishedAt-$author"
        }
    }
}
