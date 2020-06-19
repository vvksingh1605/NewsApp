package net.test.newsappvivek

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import net.test.newsappvivek.data.Article
import net.test.newsappvivek.data.NewsRepo
import net.test.newsappvivek.db.AppDatabase
import net.test.newsappvivek.network.NewsAPIService

class NewsViewModel( application: Application) :AndroidViewModel(application){

    val detailEvent=MutableLiveData<Article>()

    val mNewsApiService by lazy {
        NewsAPIService.create()
    }

    val mdB by lazy {
        AppDatabase.getInstance(application)
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val news = flowOf(
        NewsRepo.getInstance(mdB, mNewsApiService).getNews(application,10)
    ).flattenMerge(2)

}