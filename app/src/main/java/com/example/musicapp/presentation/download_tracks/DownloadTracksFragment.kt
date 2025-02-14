package com.example.musicapp.presentation.download_tracks

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.databinding.FragmentDownloadTracksBinding
import com.example.musicapp.presentation.base.BaseFragment
import com.example.musicapp.presentation.download_tracks.adapter.DownloadTracksAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.core.widget.doAfterTextChanged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DownloadTracksFragment : BaseFragment<FragmentDownloadTracksBinding>() {

    private val viewModel: DownloadTracksViewModel by viewModels()
    private lateinit var adapter: DownloadTracksAdapter
    private var searchJob: Job? = null

    override fun createBinding(): FragmentDownloadTracksBinding {
        return FragmentDownloadTracksBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearch()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = DownloadTracksAdapter() { track ->
            // Переход к экрану воспроизведения
            val action = DownloadTracksFragmentDirections.actionLocalTracksFragmentToPlayerFragment(track)
            findNavController().navigate(action)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupSearch() {
        binding.searchView.editText.doAfterTextChanged { editable ->
            searchJob?.cancel()
            searchJob = MainScope().launch {
                delay(500) // Задержка 500 мс
                editable?.toString()?.let { query ->
                    viewModel.searchTracks(query)
                }
            }
        }
    }

    private fun observeViewModel() {
        viewModel.tracks.observe(viewLifecycleOwner) { tracks ->
            adapter.submitList(tracks)
            binding.progressBar.visibility = View.GONE
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }
}

