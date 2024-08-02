package hsgpublic.example.newsapi.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hsgpublic.example.newsapi.data.repository.TopHeadlinesDefaultRepository
import hsgpublic.example.newsapi.data.repository.TopHeadlinesRepository
import hsgpublic.example.newsapi.util.currentLocaledDateString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: TopHeadlinesRepository = TopHeadlinesDefaultRepository()
): ViewModel() {
    val headlines: Flow<List<HeadlineItemData>> = repository.headlines
        .map { headlines ->
            headlines.map { headline ->
                HeadlineItemData(
                    title = headline.title,
                    publishedAt = headline.publishedAt,
                    formattedPublishedAt = headline.publishedAt.currentLocaledDateString(
                        "yyyy-MM-dd'T'HH:mm:ss'Z'"
                    ),
                    author = headline.author,
                    urlToImage = headline.urlToImage,
                    articleURL = headline.url
                )
            }
        }
        .flowOn(Dispatchers.Main)

    fun fetchTopHeadlines(country: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchTopHeadlines(country)
        }
    }
}