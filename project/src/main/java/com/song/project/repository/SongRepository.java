package com.song.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.song.project.model.Song;

public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findByAudioUrl(String url);
}
