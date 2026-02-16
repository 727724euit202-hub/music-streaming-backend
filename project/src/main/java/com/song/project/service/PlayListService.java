package com.song.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.song.project.model.PlayList;
import com.song.project.model.Song;
import com.song.project.repository.PlayListRepository;
import com.song.project.repository.SongRepository;

@Service
public class PlayListService {
    private final PlayListRepository playListRepository;

    private final SongRepository songRepository;

    public PlayListService(PlayListRepository playListRepository,SongRepository songRepository) {
        this.playListRepository = playListRepository;
        this.songRepository = songRepository;
    }

    public PlayList createPlayList(String name){
        PlayList playList = new PlayList();
        playList.setName(name);
        return playListRepository.save(playList);
    }

    public PlayList addSong(Long playListId, Long songId){
        PlayList playList = playListRepository.findById(playListId).orElseThrow(()-> new RuntimeException("Playlist not found"));
        Song song = songRepository.findById(songId).orElseThrow(()-> new RuntimeException("Song not found"));
        playList.getSongs().add(song);
        song.getPlaylists().add(playList);
        return playListRepository.save(playList);

    }

    public PlayList getPlayList(Long id) {
        return playListRepository.findById(id).orElseThrow(()-> new RuntimeException("Playlist not found"));
    }

    public List<PlayList> getAllPlaylists() {
        return playListRepository.findAll();
    }
        

}
