package hsgpublic.example.newsapi.data.remote.endpoint

data class TopHeadlinesEndpoint(
    override val path: String = "/v2/top-headlines",
    override val headers: Map<String, String> = mapOf(
        "Content-Type" to EndpointContentType.Json.raw
    ),
    override val method: EndpointMethod = EndpointMethod.Get,
    private val country: String
): Endpoint {
    override val query: Map<String, String>
        get() = mapOf("country" to country)
}