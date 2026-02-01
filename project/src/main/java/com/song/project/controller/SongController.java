package com.song.project.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.song.project.model.Song;
import com.song.project.service.SongService;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;

    private static final String UPLOAD_DIR = "uploads/songs/";

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadSong(
            @RequestParam("file") MultipartFile file,
            @RequestParam String title,
            @RequestParam String artist) throws IOException {

        Files.createDirectories(Paths.get(UPLOAD_DIR));

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        Files.write(filePath, file.getBytes());

        Song song = new Song();
        song.setTitle(title);
        song.setArtist(artist);
        song.setAudioUrl("/api/songs/stream/" + fileName);

        songService.save(song);

        return ResponseEntity.ok("Song uploaded successfully");
    }

    @GetMapping
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("/stream/{fileName}")
    public ResponseEntity<Resource> streamSong(@PathVariable String fileName)
            throws IOException {

        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Resource resource = new UrlResource(filePath.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .body(resource);
    }
}
