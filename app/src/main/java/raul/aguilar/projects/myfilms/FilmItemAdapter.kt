package raul.aguilar.projects.myfilms

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import raul.aguilar.projects.myfilms.databinding.ViewFilmItemSearchBinding
import raul.aguilar.projects.myfilms.http.MovieResult
import raul.aguilar.projects.myfilms.http.Result


class FilmItemAdapter(val listFilms:MovieResult, private val listener:(Result)->Unit):RecyclerView.Adapter<FilmItemAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = parent.inflate(R.layout.view_film_item_search)

        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val filmItem = listFilms.results[position]
        holder.bind(filmItem)
        holder.itemView.setOnClickListener{
            listener(filmItem)
        }

    }
    override fun getItemCount() = listFilms.results.size


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding  = ViewFilmItemSearchBinding.bind(view)

        fun bind(item:Result) {

            with(binding) {

                tvTitle.text = item.title
                tvYear.text = getYear(item.release_date)
                ivFilm.clipToOutline = true
                ivFilm.load("https://image.tmdb.org/t/p/original/${item.poster_path}")

            }

        }
        private fun getYear(date:String):String{

            return date.split('-').get(0)

        }
    }


}