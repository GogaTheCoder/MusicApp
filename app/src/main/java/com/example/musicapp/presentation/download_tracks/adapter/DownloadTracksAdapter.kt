package com.example.musicapp.presentation.download_tracks.adapter

import  android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.ItemDownloadTrackBinding
import com.example.musicapp.domain.model.Track
import com.bumptech.glide.Glide

class DownloadTracksAdapter(
    private var tracks: List<Track>,
    private val onItemClick: (Track) -> Unit
) : RecyclerView.Adapter<DownloadTracksAdapter.ViewHolder>() {

    // ViewHolder для элемента списка
    inner class ViewHolder(private val binding: ItemDownloadTrackBinding) : RecyclerView.ViewHolder(binding.root) {

        // Привязка данных к элементу списка
        fun bind(track: Track) {
            binding.title.text = track.title
            binding.artist.text = track.artist

            // Загрузка обложки трека с помощью Glide
            track.coverUrl?.let { coverUrl ->
                Glide.with(binding.root.context)
                    .load(coverUrl)
                    .into(binding.cover)
            }

            // Обработка клика по элементу списка
            binding.root.setOnClickListener {
                onItemClick(track)
            }
        }
    }

    // Создание ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDownloadTrackBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    // Привязка данных к ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tracks[position])
    }

    // Количество элементов в списке
    override fun getItemCount(): Int = tracks.size

    // Обновление списка треков
    fun updateList(newList: List<Track>) {
        tracks = newList
        notifyDataSetChanged()
    }
}