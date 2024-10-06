package DAO

import Entity.FavoriteNew
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteNewsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoriteNew: FavoriteNew)

    @Delete
    fun delete(favoriteNew: FavoriteNew)

    @Query("SELECT * FROM favorite_news")
    fun getAll(): List<FavoriteNew>
}