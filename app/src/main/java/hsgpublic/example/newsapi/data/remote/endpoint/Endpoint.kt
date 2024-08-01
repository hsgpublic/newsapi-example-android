package hsgpublic.example.newsapi.data.remote.endpoint

import hsgpublic.example.newsapi.config.ServerConfig

interface Endpoint {
    val path: String
    val baseQuery: Map<String, String>
        get() = mapOf("apiKey" to ServerConfig.apiKey)
    val query: Map<String, String>
        get() = mapOf()
    val headers: Map<String, String>
        get() = mapOf()
    val parameters: Map<String, Any>
        get() = mapOf()
    val method: EndpointMethod
}