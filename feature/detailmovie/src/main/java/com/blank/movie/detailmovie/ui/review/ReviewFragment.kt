package com.blank.movie.detailmovie.ui.review

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.blank.movie.detailmovie.databinding.FragmentReviewBinding
import com.blank.movie.detailmovie.ui.adapter.ReviewMovieAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ReviewFragment : Fragment() {
    private val viewModel: ReviewViewModel by viewModels()
    private val reviewMovieAdapter = ReviewMovieAdapter()
    private var urlBackDrop = ""
    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentReviewBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initObserver()
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.reviewsData.collectLatest {
                reviewMovieAdapter.submitData(it)
            }
        }
    }

    private fun initData() {
        val idMovie = arguments?.getInt(ID_MOVIE) ?: 0
        urlBackDrop = arguments?.getString(URL_BACKDROP).orEmpty()
        viewModel.setIdMovie(idMovie)
    }

    private fun initView() {
        binding.apply {
            backButton.setOnClickListener {
                this@ReviewFragment.findNavController().popBackStack()
            }
            rvVideo.adapter = reviewMovieAdapter
            context?.let {
                Glide.with(it)
                    .load(urlBackDrop)
                    .placeholder(ColorDrawable(Color.BLACK))
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageBackdrop)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val ID_MOVIE = "idMovie"
        const val URL_BACKDROP = "URL_BACKDROP"

    }
}