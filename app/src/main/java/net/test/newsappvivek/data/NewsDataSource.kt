package net.test.newsappvivek.data

import android.app.Application
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
// base interface for news data repo
interface NewsDataSource {
    fun getNews(application: Application,pageSize:Int):Flow<PagingData<Article>>
}