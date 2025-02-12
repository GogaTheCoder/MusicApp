package com.example.musicapp.presentation.downloadtracks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.databinding.FragmentDownloadTracksBinding
import com.example.musicapp.presentation.base.BaseFragment

class LocalTracksFragment : BaseFragment<FragmentDownloadTracksBinding>() {

    private val viewModel: DownloadTracksViewModel by viewModels()
    private lateinit var adapter: DownloadTracksAdapter

    override fun createBinding(): FragmentDownloadTracksBinding {
        return FragmentDownloadTracksBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DownloadTracksAdapter { track ->
            // Обработка клика по треку
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.tracks.observe(viewLifecycleOwner) { tracks ->
            adapter.updateList(tracks)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchTracks(it) }
                return true
            }
        })
    }
}