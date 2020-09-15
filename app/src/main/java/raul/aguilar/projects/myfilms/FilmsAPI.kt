package raul.aguilar.projects.myfilms

import raul.aguilar.projects.myfilms.http.MovieResult
import raul.aguilar.projects.myfilms.http.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmsAPI{

    @GET("search/movie")
    fun searchMovies(@Query("api_key") key:String,@Query("query") query:String): Call<MovieResult>

    @GET("movie")
    fun getDetaikMovie(@Query("movie_id") movieId:String, @Query( "api_key") key:String): Call<Result>

    @GET("movie/popular")
    fun getPopularFilms(@Query("api_key") key:String, @Query("page")page:String):Call<MovieResult>


}