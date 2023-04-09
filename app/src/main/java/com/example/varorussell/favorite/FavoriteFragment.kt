package com.example.varorussell.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.varorussell.databinding.FragmentFavoriteBinding
import com.example.varorussell.movieList.MovieListItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModels()
    private val adapter = GroupAdapter<GroupieViewHolder>()

    private lateinit var bindiing: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindiing = FragmentFavoriteBinding.inflate(inflater).apply {
            favoritesRecycler.apply {
                layoutManager = LinearLayoutManager(this@FavoriteFragment.context)
                adapter = this@FavoriteFragment.adapter
            }

        }
        initObservers()
        return bindiing.root
    }


    // todo handle the data change better
    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.favorites.collect {
                adapter.clear()
                adapter.addAll(it.map { data -> FavoriteListItem(data, viewModel::removeFavorite) })
                adapter.notifyDataSetChanged()
            }

        }
    }

}