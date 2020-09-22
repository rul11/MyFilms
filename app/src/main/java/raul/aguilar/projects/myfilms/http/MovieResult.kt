package raul.aguilar.projects.myfilms.http

import com.google.gson.annotations.SerializedName

data class MovieResult(

    val results: List<Result>

){
    constructor(): this(emptyList())


}

data class Result(

    val id: Int,
    val poster_path: String,
    val title: String,
    val release_date: String,
    val runtime: Int,
    val overview: String,
    val genres:List<Genre>,
    val images:Backdrops
)

data class Genre(

    val id:Int,
    val name:String

)

data class Backdrops(

    val backdrops:List<Image>
)

data class Image(

    val file_path:String
)