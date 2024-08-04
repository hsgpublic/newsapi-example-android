package hsgpublic.example.newsapi.data.local.entity

import hsgpublic.example.newsapi.data.model.HeadlineModel

data class HeadlineEntity(
    val title: String,
    val publishedAt: String,
    val author: String,
    val urlToImage: String,
    val url: String,
    var articleVisited: Boolean = false
) {
    fun asHeadlineModel(): HeadlineModel {
        return HeadlineModel(
            title = title,
            publishedAt = publishedAt,
            author = author,
            urlToImage = urlToImage,
            url = url,
            articleVisited = articleVisited
        )
    }

    fun asRoomEntity(): HeadlineRoomEntity {
        return HeadlineRoomEntity(
            compositeKey = HeadlineRoomEntity.makeCompositeKey(publishedAt, author),
            title = title,
            publishedAt = publishedAt,
            author = author,
            urlToImage = urlToImage,
            url = url,
            articleVisited = articleVisited
        )
    }
}
