package com.example.musicapp.presentation.apitracks

import com.example.musicapp.presentation.base.BaseFragment

class ApiTracksFragment : BaseFragment<FragmentApiTracksBinding>() {
    override fun createBinding(): FragmentApiTracksBinding {
        return FragmentApiTracksBinding.inflate(layoutInflater)
    }
}