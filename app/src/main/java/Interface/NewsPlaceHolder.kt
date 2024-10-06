package Interface

import Beans.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsPlaceHolder {
    @GET("articles.php")
    fun getArticles(@Query("description") description: String): Call<List<News>>
}