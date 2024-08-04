package hsgpublic.example.newsapi.data.model

import hsgpublic.example.newsapi.data.local.entity.HeadlineEntity

data class HeadlineModel(
    val title: String,
    val publishedAt: String,
    val author: String,
    val urlToImage: String,
    val url: String,
    var articleVisited: Boolean = false
) {
    fun asHeadlineEntity(): HeadlineEntity {
        return HeadlineEntity(
            title = title.orEmpty(),
            publishedAt = publishedAt.orEmpty(),
            author = author.orEmpty(),
            urlToImage = urlToImage.orEmpty(),
            url = url.orEmpty(),
            articleVisited = articleVisited ?: false
        )
    }
}
