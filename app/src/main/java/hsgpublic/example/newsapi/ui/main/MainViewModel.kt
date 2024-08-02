package hsgpublic.example.newsapi.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hsgpublic.example.newsapi.data.model.HeadlineModel
import hsgpublic.example.newsapi.data.repository.TopHeadlinesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: TopHeadlinesRepository = TopHeadlinesRepository()
): ViewModel() {
    val headlines: Flow<List<HeadlineModel>> = repository.headlines
        .flowOn(Dispatchers.Main)

    fun fetchTopHeadlines(country: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchTopHeadlines(country)
        }
    }
}