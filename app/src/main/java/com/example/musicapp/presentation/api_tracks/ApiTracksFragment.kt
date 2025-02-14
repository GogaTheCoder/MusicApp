package com.example.musicapp.presentation.api_tracks

import com.example.musicapp.databinding.FragmentApiTracksBinding
import com.example.musicapp.presentation.base.BaseFragment

class ApiTracksFragment : BaseFragment<FragmentApiTracksBinding>() {
    override fun createBinding(): FragmentApiTracksBinding {
        return FragmentApiTracksBinding.inflate(layoutInflater)
    }
}