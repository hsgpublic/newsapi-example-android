package hsgpublic.example.newsapi.data.remote.model

import hsgpublic.example.newsapi.data.local.entity.HeadlineEntity
import hsgpublic.example.newsapi.data.model.HeadlineModel

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
            title = title,
            publishedAt = publishedAt,
            author = author,
            urlToImage = urlToImage,
            url = url
        )
    }
}
