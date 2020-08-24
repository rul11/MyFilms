package raul.aguilar.projects.myfilms

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_search_films.*
import raul.aguilar.projects.myfilms.databinding.FragmentSearchFilmsBinding
import raul.aguilar.projects.myfilms.http.MovieResult
import raul.aguilar.projects.myfilms.http.Result
import raul.aguilar.projects.myfilms.http.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFilmsFragment : Fragment() {

    private var _binding: FragmentSearchFilmsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchFilmsBinding.inflate(inflater,container,false)

        binding.rvSearchResults.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
       /* binding.rvSearchResults.adapter = FilmItemAdapter(MovieResult(),{



        })*/


        val view = binding.root

        binding.edtxSearchBar.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {

                if (!s.toString().isEmpty()) {

                    val request = RetrofitClient.buildService(FilmsAPI::class.java)
                    val call =
                        request.searchMovies("5213636c85e9ff44295377934e99d1e5", s.toString())

                    call.enqueue(object : Callback<MovieResult> {

                        override fun onResponse(
                            call: Call<MovieResult>,
                            response: Response<MovieResult>
                        ) {

                            if (response.isSuccessful) {

                                val movieResult = response.body()!!

                                val adapter = FilmItemAdapter(movieResult, {

                                    val bundle = Bundle()
                                    bundle.putString("urlMovie", it.poster_path)

                                    val fragment = DetailFilmFragment()
                                    fragment.arguments = bundle

                                    activity?.supportFragmentManager?.beginTransaction()
                                        ?.replace(R.id.container, fragment)?.commit()

                                })
                                binding.rvSearchResults.adapter = adapter
                            }

                        }

                        override fun onFailure(call: Call<MovieResult>, t: Throwable) {


                        }

                    })
                }
                else{

                    binding.rvSearchResults.adapter = FilmItemAdapter(MovieResult(),{})

                }



            }

        })






        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}