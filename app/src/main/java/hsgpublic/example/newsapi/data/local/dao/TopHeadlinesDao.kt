package hsgpublic.example.newsapi.data.local.dao

import hsgpublic.example.newsapi.data.local.entity.HeadlineEntity

interface TopHeadlinesDao {
    suspend fun readAll(): List<HeadlineEntity>
    suspend fun read(compositeKeys: List<String>): List<HeadlineEntity>
    suspend fun upsert(entities: List<HeadlineEntity>)
    suspend fun delete(compositeKeys: List<String>)
}