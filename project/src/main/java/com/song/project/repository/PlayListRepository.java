package com.song.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.song.project.model.PlayList;

@Repository
public interface  PlayListRepository extends JpaRepository<PlayList,Long>{
    
}
