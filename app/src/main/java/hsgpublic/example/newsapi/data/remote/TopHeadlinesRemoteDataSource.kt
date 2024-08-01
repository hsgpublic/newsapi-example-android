package hsgpublic.example.newsapi.data.remote

import hsgpublic.example.newsapi.data.remote.helper.HttpRequester
import hsgpublic.example.newsapi.data.remote.helper.RetrofitHelper

class TopHeadlinesRemoteDataSource(
    private val httpRequester: HttpRequester = RetrofitHelper
) {

}