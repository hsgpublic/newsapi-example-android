package hsgpublic.example.newsapi.data.remote.model

data class ArticleResponseModel(
    val source: SourceResponseModel?,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)
