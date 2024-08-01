package hsgpublic.example.newsapi.data.remote.model

data class SourceResponseModel(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
)
