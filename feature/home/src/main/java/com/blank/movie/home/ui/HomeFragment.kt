package com.blank.movie.home.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.blank.movie.home.adapter.MovieAdapter
import com.blank.movie.home.adapter.PagingLoadStateAdapter
import com.blank.movie.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var adapterMovie: MovieAdapter = MovieAdapter()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
    }

    private fun initView() {
        binding.rvMovie.adapter = adapterMovie.withLoadStateFooter(
            PagingLoadStateAdapter(retryCallback = { adapterMovie.retry() })
        )

    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.moviesData.collectLatest {
                Log.e("Data movie", "movie $it")
                adapterMovie.submitData(it)
            }
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}