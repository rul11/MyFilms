package raul.aguilar.projects.myfilms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import raul.aguilar.projects.myfilms.databinding.FragmentDetailFilmBinding


class DetailFilmFragment : Fragment() {

    private var _binding: FragmentDetailFilmBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        _binding = FragmentDetailFilmBinding.inflate(inflater,container,false)

        activity?.actionBar?.hide()

        var urlMovie =  arguments?.getString("urlMovie")
        binding.ivMovieScene.load("https://image.tmdb.org/t/p/original/$urlMovie")

        val view = binding.root

        return view
    }


}