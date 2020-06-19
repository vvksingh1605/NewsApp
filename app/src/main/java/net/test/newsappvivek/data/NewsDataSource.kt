package net.test.newsappvivek.data

import android.app.Application
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface NewsDataSource {
    fun getNews(application: Application,pageSize:Int):Flow<PagingData<Article>>
}