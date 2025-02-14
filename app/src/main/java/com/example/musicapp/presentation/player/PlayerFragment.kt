package com.example.musicapp.presentation.player

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.musicapp.R
import com.example.musicapp.databinding.FragmentPlayerBinding
import com.example.musicapp.presentation.base.BaseFragment
import com.example.musicapp.service.MusicService

class PlayerFragment : BaseFragment<FragmentPlayerBinding>() {

    private var musicService: MusicService? = null
    private lateinit var exoPlayer: ExoPlayer
    private var playerListener: Player.Listener? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            musicService = (service as MusicService.DownloadBinder).getService()
            exoPlayer = musicService!!.exoPlayer
            setupPlayer()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            musicService = null
        }
    }

    override fun createBinding(): FragmentPlayerBinding {
        return FragmentPlayerBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPlayerControls()
    }

    private fun initPlayerControls() {
        // Кнопка Play/Pause
        binding.btnPlayPause.setOnClickListener {
            togglePlayPause()
        }

        // Кнопка "Предыдущий трек"
        binding.btnPrevious.setOnClickListener {
            exoPlayer.seekToPrevious()
        }

        // Кнопка "Следующий трек"
        binding.btnNext.setOnClickListener {
            exoPlayer.seekToNext()
        }

        // SeekBar
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    exoPlayer.seekTo(progress.toLong())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

//    private fun setupPlayer() {
//        // Установка начального состояния кнопки Play/Pause
//        if (exoPlayer.isPlaying) {
//            binding.btnPlayPause.setImageResource(R.drawable.ic_pause)
//        } else {
//            binding.btnPlayPause.setImageResource(R.drawable.ic_play)
//        }
//
//        // Обновление SeekBar и времени
//        exoPlayer.addListener(object : Player.Listener {
//            override fun onEvents(player: Player, events: Player.Events) {
//                requireActivity().runOnUiThread {
//                    binding.seekBar.max = player.duration.toInt()
//                    binding.seekBar.progress = player.currentPosition.toInt()
//                    binding.tvCurrentTime.text = formatTime(player.currentPosition)
//                    binding.tvDuration.text = formatTime(player.duration)
//                }
//            }
//        })
//    }

    private fun setupPlayer() {
        playerListener = object : Player.Listener {
            override fun onEvents(player: Player, events: Player.Events) {
                requireActivity().runOnUiThread {
                    binding.seekBar.max = player.duration.toInt()
                    binding.seekBar.progress = player.currentPosition.toInt()
                    binding.tvCurrentTime.text = formatTime(player.currentPosition)
                    binding.tvDuration.text = formatTime(player.duration)
                }
            }
        }
        exoPlayer.addListener(playerListener!!)

        // Обновление кнопки Play/Pause
        if (exoPlayer.isPlaying) {
            binding.btnPlayPause.setImageResource(R.drawable.ic_pause)
        } else {
            binding.btnPlayPause.setImageResource(R.drawable.ic_play)
        }
    }

    private fun togglePlayPause() {
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
            binding.btnPlayPause.setImageResource(R.drawable.ic_play)
        } else {
            exoPlayer.play()
            binding.btnPlayPause.setImageResource(R.drawable.ic_pause)
        }
    }

    private fun formatTime(ms: Long): String {
        val minutes = (ms / 1000) / 60
        val seconds = (ms / 1000) % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    override fun onStart() {
        super.onStart()
        Intent(requireContext(), MusicService::class.java).also { intent ->
            requireContext().bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        requireContext().unbindService(connection)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        playerListener?.let {
            exoPlayer.removeListener(it)
            playerListener = null
        }
    }
}