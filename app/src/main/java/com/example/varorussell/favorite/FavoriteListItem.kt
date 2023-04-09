package com.example.varorussell.favorite

import android.view.View
import com.example.varorussell.R
import com.example.varorussell.databinding.ItemMovieListBinding
import com.example.varorussell.network.di.NetworkModule
import com.example.varorussell.network.response.NowPlayingResponse
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem

class FavoriteListItem(
    private val movie: NowPlayingResponse.Result,
    private val removeFromFavorites: (NowPlayingResponse.Result) -> Unit
) : BindableItem<ItemMovieListBinding>() {
    override fun bind(binding: ItemMovieListBinding, position: Int) {
        binding.apply {
            movie = this@FavoriteListItem.movie
            favorite.apply {
                setImageResource(R.drawable.favorite)
                setOnClickListener {
                    removeFromFavorites.invoke(this@FavoriteListItem.movie)
                }
            }
            Picasso.get().load(NetworkModule.BASE_IMAGE_URL + movie?.poster_path).resize(500,500).placeholder(
                R.drawable.movie)
                .into(poster)
        }
    }

    override fun getLayout(): Int = R.layout.item_movie_list

    override fun initializeViewBinding(view: View): ItemMovieListBinding {
        return ItemMovieListBinding.bind(view)
    }

}