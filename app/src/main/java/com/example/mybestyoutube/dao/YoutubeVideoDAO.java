package com.example.mybestyoutube.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mybestyoutube.pojos.YoutubeVideo;

import java.util.List;

@Dao
public interface YoutubeVideoDAO {

    @Query("SELECT * FROM YoutubeVideo WHERE id = :id")
    public YoutubeVideo find(Long id);

    @Query("SELECT * FROM YoutubeVideo")
    public List<YoutubeVideo> list();

    @Insert
    public void add(YoutubeVideo... youtubeVideos);

    @Update
    public void update(YoutubeVideo... youtubeVideos);

    @Delete
    public void delete(YoutubeVideo... youtubeVideos);

}
