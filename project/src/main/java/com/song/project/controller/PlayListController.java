package com.song.project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.song.project.model.PlayList;
import com.song.project.service.PlayListService;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/playlists")
public class PlayListController {

    private final PlayListService playListService;

    public PlayListController(PlayListService playListService) {
        this.playListService = playListService;
    }

    @PostMapping
    public ResponseEntity<PlayList> createPlaylist(@RequestParam String name) {
        PlayList playlist = playListService.createPlayList(name);
        return ResponseEntity.ok(playlist);
    }

    @PostMapping("/{playlistId}/add/{songId}")
    public ResponseEntity<PlayList> addSongToPlaylist(
            @PathVariable Long playlistId,
            @PathVariable Long songId) {

        PlayList playlist = playListService.addSong(playlistId, songId);
        return ResponseEntity.ok(playlist);
    }

    @GetMapping
    public ResponseEntity<List<PlayList>> getAllPlaylists() {
        return ResponseEntity.ok(playListService.getAllPlaylists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayList> getPlaylist(@PathVariable Long id) {
        return ResponseEntity.ok(playListService.getPlayList(id));
    }
}
