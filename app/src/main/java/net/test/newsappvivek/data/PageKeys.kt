package net.test.newsappvivek.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "index_keys")
data class PageKeys(
    @PrimaryKey val nextPageKey:Int?
)