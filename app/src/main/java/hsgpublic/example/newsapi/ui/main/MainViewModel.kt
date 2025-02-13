package hsgpublic.example.newsapi.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hsgpublic.example.newsapi.data.repository.TopHeadlinesDefaultRepository
import hsgpublic.example.newsapi.data.repository.TopHeadlinesRepository
import hsgpublic.example.newsapi.util.currentLocaledDateString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,
    private val repository: TopHeadlinesRepository =
        TopHeadlinesDefaultRepository(application.applicationContext)
): ViewModel() {
    val headlines: Flow<List<HeadlineItemData>> = repository.headlines
        .distinctUntilChanged()
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
                    articleURL = headline.url,
                    articleVisited = headline.articleVisited
                )
            }
        }

    fun fetchTopHeadlines(country: String) {
        repository.fetchTopHeadlines(country)
    }

    fun markVisited(index: Int) {
        repository.markVisited(index)
    }
}