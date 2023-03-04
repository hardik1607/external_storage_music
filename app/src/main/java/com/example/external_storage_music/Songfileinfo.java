package com.example.external_storage_music;

import android.os.Parcel;

public class Songfileinfo {
    private String title, album, artist, file_uri;
    private String album_art, duration;
    public long id = 0;

    public Songfileinfo() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Songfileinfo(Parcel in) {
        title = in.readString();
        album = in.readString();
        artist = in.readString();
        file_uri = in.readString();
        album_art = in.readString();
        id = in.readLong();
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getFile_uri() {
        return file_uri;
    }

    public void setFile_uri(String file_uri) {
        this.file_uri = file_uri;
    }

    public String getAlbum_art() {
        return album_art;
    }

    public void setAlbum_art(String album_art) {
        this.album_art = album_art;
    }

}
