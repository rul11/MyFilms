package raul.aguilar.projects.myfilms

import raul.aguilar.projects.myfilms.http.MovieResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmsAPI{

    @GET("search/movie")
    fun searchMovies(@Query("api_key") key:String,@Query("query") query:String): Call<MovieResult>
}