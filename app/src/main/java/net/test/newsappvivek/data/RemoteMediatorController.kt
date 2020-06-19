package net.test.newsappvivek.data

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.*
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import net.test.newsappvivek.AppConstant
import net.test.newsappvivek.db.AppDatabase
import net.test.newsappvivek.network.NewsAPIService
import net.test.newsappvivek.Utils
import retrofit2.HttpException
import java.io.IOException

//class to maintaing data loading by db and api concurrently
// if data is low while using scroll to see more news on load method will get new data and update
@OptIn(ExperimentalPagingApi::class)
class RemoteMediatorController(
    private val application: Application,
    private val db: AppDatabase,
    private val newsApi: NewsAPIService
) : RemoteMediator<Int, Article>() {
    private val articleDao: ArticlesDao = db.articlesDao()
    private val pagerDao: PagerDao = db.pagerDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        try {
            val loadKey = when (loadType) {
                REFRESH -> 1
                PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                APPEND -> {
                    val nextKeys = db.withTransaction {
                        pagerDao.getNextKey()
                    }
                    if (nextKeys.nextPageKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    nextKeys.nextPageKey
                }
            }

            if (Utils.isNetworkAvailable(application)) {
                val latestNews = newsApi.getLatestNews(
                    pageSize = when (loadType) {
                        REFRESH -> state.config.initialLoadSize
                        else -> state.config.pageSize
                    },
                    //page(1,2,3,4) key is load key
                    nextPage = loadKey,
                    country = "us",
                    apiKey = AppConstant.API_KEY
                )
                val items = latestNews.articles

                db.withTransaction {
                    if (loadType == REFRESH) {
                        articleDao.delete()

                    }
                    pagerDao.delete()
                    //load key increment is used to send page number 1,2,3 and so on in news api
                    pagerDao.insert(PageKeys(loadKey + 1))
                    articleDao.insertAll(items)

                }
                return MediatorResult.Success(endOfPaginationReached = items.isEmpty())
            }

            return MediatorResult.Success(false)


        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

}
