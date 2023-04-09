package com.example.varorussell.movieList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.varorussell.databinding.FragmentMovieListBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private val viewModel: MovieListViewModel by viewModels()
    private val adapter = GroupAdapter<GroupieViewHolder>()

    private lateinit var binding: FragmentMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater).apply {
            movieListRecycler.apply {
                layoutManager = LinearLayoutManager(this@MovieListFragment.context)
                adapter = this@MovieListFragment.adapter
            }
        }
        initObserver()
        return binding.root
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.movies.collect {
                if(it.isNotEmpty())
                    binding.animationView.visibility = GONE
                adapter.addAll(it.map { data -> MovieListItem(data, viewModel::addToFavorites) })
            }
        }
    }

}