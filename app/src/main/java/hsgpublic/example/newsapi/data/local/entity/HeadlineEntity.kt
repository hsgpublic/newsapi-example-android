package hsgpublic.example.newsapi.data.local.entity

data class HeadlineEntity(
    val title: String,
    val publishedAt: String,
    val author: String,
    val urlToImage: String,
    val url: String,
    var articleVisited: Boolean = false
)
