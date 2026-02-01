package com.song.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.song.project.model.Song;

public interface SongRepository extends JpaRepository<Song, Long> {
    
}
