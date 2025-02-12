package com.example.musicapp.presentation.downloadtracks

import com.example.musicapp.databinding.FragmentDownloadTracksBinding
import com.example.musicapp.presentation.base.BaseFragment

class LocalTracksFragment : BaseFragment<FragmentDownloadTracksBinding>() {
    override fun createBinding(): FragmentDownloadTracksBinding {
        return FragmentDownloadTracksBinding.inflate(layoutInflater)
    }
}