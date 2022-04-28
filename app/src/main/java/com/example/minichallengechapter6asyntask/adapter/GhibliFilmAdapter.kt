package com.example.minichallengechapter6asyntask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.minichallengechapter6asyntask.R
import com.example.minichallengechapter6asyntask.model.GetAllGhibliFilmResponseItem
import kotlinx.android.synthetic.main.item_ghibli_film_adapter.view.*

class GhibliFilmAdapter : RecyclerView.Adapter<GhibliFilmAdapter.ViewHolder>() {
    private var listGhibliFilm : List<GetAllGhibliFilmResponseItem>? = null
    fun setDataGhibliFilm(list : List<GetAllGhibliFilmResponseItem>){
        this.listGhibliFilm = list
    }
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GhibliFilmAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ghibli_film_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GhibliFilmAdapter.ViewHolder, position: Int) {
        with(holder.itemView){
            with(listGhibliFilm!![position]){
                ghibli_films_title.text = "Title : $title / $originalTitleRomanised / $originalTitle / $"
                ghibli_films_producer.text = "Producer : $producer"
                ghibli_films_director.text = "Director : $director"
                ghibli_films_release_data.text = "Release date : $releaseDate"
                Glide.with(ghibli_films_image.context)
                    .load(image)
                    .error(R.drawable.ic_launcher_background)
                    .override(50, 100)
                    .into(ghibli_films_image)
            }
        }
    }

    override fun getItemCount(): Int {
        return if(listGhibliFilm.isNullOrEmpty()){
            0
        }else{
            listGhibliFilm!!.size
        }
    }

}