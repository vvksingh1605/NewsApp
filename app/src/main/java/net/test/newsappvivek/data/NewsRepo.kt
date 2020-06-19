package net.test.newsappvivek.data

import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingConfig
import net.test.newsappvivek.db.AppDatabase
import net.test.newsappvivek.network.NewsAPIService

/**
 * Created by Vivek Singh
 * data source repository for getting news
 */
class NewsRepo private constructor(val db: AppDatabase, val newsApi: NewsAPIService) :
    NewsDataSource {

    override fun getNews(application: Application,pageSize: Int) = Pager(
        config = PagingConfig(pageSize),
        remoteMediator = RemoteMediatorController(application,db, newsApi)
    ) {
        db.articlesDao().getAllPaged() // get all paged news stored in db
    }.flow

    companion object {
        var mInstance: NewsRepo? = null
          fun getInstance(db: AppDatabase, newsApi: NewsAPIService) = mInstance?:
              NewsRepo(db, newsApi).also { mInstance=it }

    }
}