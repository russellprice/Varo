package com.example.varorussell.movieList

import android.view.View
import com.example.varorussell.R
import com.example.varorussell.databinding.ItemMovieListBinding
import com.example.varorussell.network.di.NetworkModule.BASE_IMAGE_URL
import com.example.varorussell.network.response.NowPlayingResponse
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem

class MovieListItem(
    private val movie: NowPlayingResponse.Result,
    private val addToFavorites: (NowPlayingResponse.Result) -> Unit
) : BindableItem<ItemMovieListBinding>() {
    override fun bind(binding: ItemMovieListBinding, position: Int) {
        binding.apply {
            movie = this@MovieListItem.movie

            favorite.apply {
                setOnClickListener {
                    addToFavorites.invoke(this@MovieListItem.movie)
                    // todo if I have time make this better
                    when (it.tag) {
                        "0" -> {
                            tag = "1"
                            setImageResource(R.drawable.favorite)
                        }
                        else -> {
                            tag = "0"
                            setImageResource(R.drawable.favorite_border)
                        }
                    }
                }
            }
            Picasso.get().load(BASE_IMAGE_URL + movie?.poster_path).resize(500,500).placeholder(R.drawable.movie)
                .into(poster)
        }
    }

    override fun getLayout(): Int = R.layout.item_movie_list

    override fun initializeViewBinding(view: View): ItemMovieListBinding {
        return ItemMovieListBinding.bind(view)
    }

}