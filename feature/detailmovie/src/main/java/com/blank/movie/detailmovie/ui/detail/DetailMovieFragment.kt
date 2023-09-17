package com.blank.movie.detailmovie.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.blank.movie.detailmovie.R
import com.blank.movie.detailmovie.databinding.FragmentDetailMovieBinding
import com.blank.movie.detailmovie.ui.adapter.RecyclerViewVideoAdapter
import com.blank.movie.domain.model.DetailMovieModel
import com.blank.movie.domain.model.DomainResource
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    private val detailMovieViewModel: DetailMovieViewModel by viewModels()
    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!

    private val videoAdapter = RecyclerViewVideoAdapter()
    private var idMovie = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initObserver()
        initView()
    }

    private fun initView() {
        binding.apply {
            backButton.setOnClickListener {
                this@DetailMovieFragment.findNavController().popBackStack()
            }
            rvVideo.adapter = videoAdapter
        }

    }

    private fun initObserver() {
        detailMovieViewModel.detailMovieData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is DomainResource.Loading -> {
                    binding.vfContent.displayedChild = VIEWFLIPPER_INDEX_LOADING
                }

                is DomainResource.Success -> {
                    binding.vfContent.displayedChild = VIEWFLIPPER_INDEX_NORMAL
                    initItemView(resource.data)
                }

                is DomainResource.SuccessNoData -> {
                    binding.vfContent.displayedChild = VIEWFLIPPER_INDEX_EMPTY
                }

                is DomainResource.Error -> {
                    binding.apply {
                        vfContent.displayedChild = VIEWFLIPPER_INDEX_ERROR
                        tvError.text = resource.message
                    }

                }
            }

        }
    }

    private fun initItemView(data: DetailMovieModel) = with(binding) {
        data.apply {
            textNameItem.text = title
            textGenre.text = genre
            textScoreItem.text = voteAverage.toString()
            textSinopsisItem.text = overview
            val urlBackDrop = BASE_URL_IMAGE + data.backdropPath
            context?.let {
                Glide.with(it)
                    .load(BASE_URL_IMAGE + data.posterPath)
                    .placeholder(ColorDrawable(Color.BLACK))
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageItem)

                Glide.with(it)
                    .load(urlBackDrop)
                    .placeholder(ColorDrawable(Color.BLACK))
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageBackdrop)
            }
            videoAdapter.submitList(videosId)
            val bundle = bundleOf(ID_MOVIE to idMovie, URL_BACKDROP to urlBackDrop)
            buttonReview.setOnClickListener {
                findNavController().navigate(R.id.reviewFragment, bundle)
            }
        }

    }

    private fun initData() {
        idMovie = arguments?.getInt(ID_MOVIE) ?: 0
        detailMovieViewModel.getDetailMovie(idMovie)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val ID_MOVIE = "idMovie"
        const val URL_BACKDROP = "URL_BACKDROP"
        private const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500/"
        private const val VIEWFLIPPER_INDEX_LOADING = 0
        private const val VIEWFLIPPER_INDEX_NORMAL = 1
        private const val VIEWFLIPPER_INDEX_ERROR = 2
        private const val VIEWFLIPPER_INDEX_EMPTY = 3
    }
}
