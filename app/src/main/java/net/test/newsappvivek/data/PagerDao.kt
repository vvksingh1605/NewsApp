package net.test.newsappvivek.data

import androidx.room.*

@Dao
interface PagerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(keys: PageKeys)

    @Query("SELECT * FROM index_keys")
    suspend fun getNextKey(): PageKeys


    @Query("DELETE FROM index_keys")
    suspend fun delete()
}