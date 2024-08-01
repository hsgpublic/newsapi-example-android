package hsgpublic.example.newsapi.data.remote.model

data class TopHeadlinesResponseModel(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleResponseModel>
)
