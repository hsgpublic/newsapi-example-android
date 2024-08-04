package hsgpublic.example.newsapi.data.local.dao

interface DatabaseAccessor {
    suspend fun <T> read(
        accessorType: DatabaseAccessorType,
        entityType: Class<T>,
        query: String
    ): List<T>
    suspend fun <T> upsert(
        accessorType: DatabaseAccessorType,
        entities: List<T>
    )
    suspend fun <T> delete(
        accessorType: DatabaseAccessorType,
        entityType: Class<T>,
        query: String
    )
}