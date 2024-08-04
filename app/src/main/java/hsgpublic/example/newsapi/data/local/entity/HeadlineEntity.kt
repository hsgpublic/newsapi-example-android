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
            title = title.orEmpty(),
            publishedAt = publishedAt.orEmpty(),
            author = author.orEmpty(),
            urlToImage = urlToImage.orEmpty(),
            url = url.orEmpty(),
            articleVisited = articleVisited ?: false
        )
    }

    fun asRoomEntity(): HeadlineRoomEntity {
        return HeadlineRoomEntity(
            compositeKey = HeadlineRoomEntity.makeCompositeKey(publishedAt, author).orEmpty(),
            title = title.orEmpty(),
            publishedAt = publishedAt.orEmpty(),
            author = author.orEmpty(),
            urlToImage = urlToImage.orEmpty(),
            url = url.orEmpty(),
            articleVisited = articleVisited ?: false
        )
    }
}
