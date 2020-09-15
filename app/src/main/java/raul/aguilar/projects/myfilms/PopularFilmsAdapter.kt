package raul.aguilar.projects.myfilms

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import raul.aguilar.projects.myfilms.databinding.ViewPopularFilmItemBinding
import raul.aguilar.projects.myfilms.http.MovieResult
import raul.aguilar.projects.myfilms.http.Result

class PopularFilmsAdapter (val listFilms:MovieResult, private val listener: (Result) -> Unit):RecyclerView.Adapter<PopularFilmsAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = parent.inflate(R.layout.view_popular_film_item)

        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: PopularFilmsAdapter.ViewHolder, position: Int) {

        val filmItem = listFilms.results[position]
        holder.bind(filmItem)
        holder.itemView.setOnClickListener{
            listener(filmItem)
        }

    }
    override fun getItemCount() = listFilms.results.size



    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

       private val bindingFilm = ViewPopularFilmItemBinding.bind(view)

        fun bind(item:Result) {

            with(bindingFilm) {

                ivPopularFilm.clipToOutline = true
                ivPopularFilm.load("https://image.tmdb.org/t/p/original/${item.poster_path}")


            }

        }

    }

}