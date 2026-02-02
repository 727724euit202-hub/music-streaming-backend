package com.song.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.song.project.model.Song;
import com.song.project.repository.SongRepository;

@Service
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song save(Song song) {
        return songRepository.save(song);
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song getSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found"));
    }

    public void updateSongPlayCount(String audioUrl){
        Song song = songRepository.findByAudioUrl(audioUrl).orElseThrow(()->new RuntimeException("Song not found"));

        song.setPlayCount(song.getPlayCount() + 1);
        songRepository.save(song);
    }
}
