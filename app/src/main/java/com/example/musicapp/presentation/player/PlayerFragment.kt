package com.example.musicapp.presentation.player

import com.example.musicapp.databinding.FragmentPlayerBinding
import com.example.musicapp.presentation.base.BaseFragment

class PlayerFragment : BaseFragment<FragmentPlayerBinding>() {
    override fun createBinding(): FragmentPlayerBinding {
        return FragmentPlayerBinding.inflate(layoutInflater)
    }
}