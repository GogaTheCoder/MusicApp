package com.example.musicapp.presentation.download_tracks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.databinding.FragmentDownloadTracksBinding
import com.example.musicapp.domain.model.Track
import com.example.musicapp.presentation.download_tracks.adapter.DownloadTracksAdapter
import com.google.android.material.search.SearchView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.musicapp.R


@AndroidEntryPoint
class DownloadTracksFragment : Fragment(R.layout.fragment_download_tracks) {

    private var _binding: FragmentDownloadTracksBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DownloadTracksViewModel by viewModels()
    private lateinit var adapter: DownloadTracksAdapter
    private var searchJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDownloadTracksBinding.bind(view)

        setupRecyclerView()
        setupSearch()
        observeViewModel()
        viewModel.downloadTracks()
    }

    private fun setupRecyclerView() {
        adapter = DownloadTracksAdapter(emptyList()) { track ->
            val action = DownloadTracksFragmentDirections
                .actionDownloadTracksFragmentToPlayerFragment(track)
            findNavController().navigate(action)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@DownloadTracksFragment.adapter
        }
    }

    private fun setupSearch() {
        binding.searchView.editText.doAfterTextChanged { editable ->
            searchJob?.cancel()
            searchJob = MainScope().launch {
                delay(500)
                editable?.toString()?.let { query ->
                    viewModel.searchTracks(query)
                }
            }
        }
    }

    private fun observeViewModel() {
        viewModel.tracks.observe(viewLifecycleOwner) { tracks ->
            binding.progressBar.visibility = View.GONE
            adapter.submitList(tracks)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
