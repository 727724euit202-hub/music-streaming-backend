package com.song.project.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Table(name="playlists")
public class PlayList {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long playListId;

    @Column(nullable = false)
    private String name;

    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
        name="playlist-songs",
        joinColumns=@JoinColumn(name="playlist_id"),
        inverseJoinColumns=@JoinColumn(name="song_id")
    )
    private List<Song> songs=new ArrayList<>();




}
