package hsgpublic.example.newsapi.data.remote.model

import hsgpublic.example.newsapi.data.local.entity.HeadlineEntity

data class ArticleResponseModel(
    val source: SourceResponseModel?,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
) {
    fun asHeadlineEntity(): HeadlineEntity {
        return HeadlineEntity(
            title = title.orEmpty(),
            publishedAt = publishedAt.orEmpty(),
            author = author.orEmpty(),
            urlToImage = urlToImage.orEmpty(),
            url = url.orEmpty()
        )
    }
}
