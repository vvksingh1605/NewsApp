package net.test.newsappvivek.model

import net.test.newsappvivek.data.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)