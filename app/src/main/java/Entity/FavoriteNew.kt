package Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_news")
data class FavoriteNew(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "published_at") val publishedAt: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "url_to_image") val urlToImage: String,
    @ColumnInfo(name = "description") val description: String
)