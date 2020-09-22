package raul.aguilar.projects.myfilms

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.appbar.AppBarLayout
import raul.aguilar.projects.myfilms.databinding.FragmentDetailFilmBinding
import raul.aguilar.projects.myfilms.http.Result
import raul.aguilar.projects.myfilms.http.MovieResult
import raul.aguilar.projects.myfilms.http.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailFilmFragment : Fragment() {

    private var _binding: FragmentDetailFilmBinding? = null
    private val binding get() = _binding!!
    private lateinit var film: Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        _binding = FragmentDetailFilmBinding.inflate(inflater,container,false)

        activity?.actionBar?.hide()

        var idFilm =  arguments?.getInt("idFilm")

        val request = RetrofitClient.buildService(FilmsAPI::class.java)
        val call = request.getDetailMovie(idFilm.toString(),"5213636c85e9ff44295377934e99d1e5")

        call.enqueue(object:Callback<Result>{

            override fun onResponse(call: Call<Result>, response: Response<Result>) {

                if (response.isSuccessful){

                    film = response.body()!!
                    val urlImage = film.images.backdrops.get(0).file_path
                    binding.ivMovieScene.load("https://image.tmdb.org/t/p/original/${urlImage}")
                    binding.ivMoviePoster.clipToOutline = true
                    binding.ivMoviePoster.load("https://image.tmdb.org/t/p/original/${film.poster_path}")
                    binding.tvOverview.text = film.overview

                    var isShow = true
                    var scrollRange = -1
                    binding.collapsingToolbar.setExpandedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor)
                    binding.collapsingToolbar.setCollapsedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor)
                    binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener{

                            barLayout, verticalOffset ->

                        if (scrollRange == -1){

                            scrollRange = barLayout?.totalScrollRange!!
                        }
                        if (scrollRange + verticalOffset == 0){

                            binding.collapsingToolbar.title = film.title
                            binding.tvTitle.text = " "
                            isShow = true
                        }
                        else if (isShow){
                            binding.collapsingToolbar.title = " "
                            binding.tvTitle.text = film.title
                            isShow = false
                        }
                    })

                }

            }

            override fun onFailure(call: Call<Result>, t: Throwable) {

                Log.e("ERROR","ERROR")
            }

        })

        val view = binding.root



        return view
    }


}