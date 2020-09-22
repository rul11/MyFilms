package raul.aguilar.projects.myfilms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import raul.aguilar.projects.myfilms.databinding.FragmentPopularFilmsBinding
import raul.aguilar.projects.myfilms.http.MovieResult
import raul.aguilar.projects.myfilms.http.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PopularFilmsFragment : Fragment() {

    private var _binding: FragmentPopularFilmsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPopularFilmsBinding.inflate(inflater,container,false)

        binding.rvPopularMovies.layoutManager = GridLayoutManager(context,3)
        binding.rvPopularMovies.adapter = PopularFilmsAdapter(MovieResult(),{})

        val view = binding.root

        val request = RetrofitClient.buildService(FilmsAPI::class.java)
        val call = request.getPopularFilms("5213636c85e9ff44295377934e99d1e5","1")

        call.enqueue(object : Callback<MovieResult> {

            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {

                if(response.isSuccessful){

                    val movieResult = response.body()!!

                    val adapter = PopularFilmsAdapter(movieResult, {

                        val bundle = Bundle()
                        bundle.putInt("idFilm", it.id)

                        val fragment = DetailFilmFragment()
                        fragment.arguments = bundle

                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(R.id.container, fragment)?.commit()

                    })
                    binding.rvPopularMovies.adapter = adapter
                }
            }
            override fun onFailure(call: Call<MovieResult>, t: Throwable) {


            }
        })


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}