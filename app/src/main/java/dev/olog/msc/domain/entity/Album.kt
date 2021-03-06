package dev.olog.msc.domain.entity

data class Album (
        val id: Long,
        val artistId: Long,
        val title: String,
        val artist: String,
        val albumArtist: String,
        val image: String,
        val songs: Int,
        val hasSameNameAsFolder: Boolean
)