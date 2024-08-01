package hsgpublic.example.newsapi.data.remote.endpoint

data class TopHeadlinesEndpoint(
    override val path: String = "/v2/top-headlines",
    override val query: Map<String, String>,
    override val method: EndpointMethod = EndpointMethod.GET
): Endpoint