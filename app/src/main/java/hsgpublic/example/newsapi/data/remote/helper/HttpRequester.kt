package hsgpublic.example.newsapi.data.remote.helper

import hsgpublic.example.newsapi.data.remote.endpoint.Endpoint

interface HttpRequester {
    suspend fun request(endpoint: Endpoint): Any
}