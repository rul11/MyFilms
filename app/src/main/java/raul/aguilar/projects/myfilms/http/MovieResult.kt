package raul.aguilar.projects.myfilms.http

data class MovieResult(

    val results: List<Result>

)

data class Result(

    val id: Int,
    val poster_path: String,
    val title: String,
    val release_date: String
)