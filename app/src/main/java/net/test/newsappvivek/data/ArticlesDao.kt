package net.test.newsappvivek.data

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM Articles")
     fun getAllPaged(): PagingSource<Int, Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<Article>)

    @Query("DELETE FROM Articles")
    suspend fun delete()
}