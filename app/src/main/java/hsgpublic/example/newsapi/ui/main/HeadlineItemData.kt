package hsgpublic.example.newsapi.ui.main

data class HeadlineItemData(
    val title: String,
    val publishedAt: String,
    val formattedPublishedAt: String,
    val author: String,
    val urlToImage: String,
    val articleURL: String,
    var articleVisited: Boolean = false
)