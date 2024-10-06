package DB

import DAO.FavoriteNewsDAO
import Entity.FavoriteNew
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteNew::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun favoriteNewsDAO(): FavoriteNewsDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "favorite_news_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}