package com.example.external_storage_music;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.util.ArrayList;

public class Songdata {

    private ArrayList<Songfileinfo> songList = new ArrayList();
    final Uri albumArtUri = Uri.parse("content://media/external/audio/albumart");

    public ArrayList getPlayList(Context context) {
        {

            try {
                String[] proj = {MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.TITLE,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.DURATION,
                        MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.ALBUM_ID
                };

                Cursor audioCursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, proj, null, null, null);

                if (audioCursor != null) {
                    if (audioCursor.moveToFirst()) {
                        do {
                            int audioTitle = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);
                            int audioartist = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);
                            int audioduration = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);
                            int audiodata = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
                            int audioalbum = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM);
                            int audioalbumid = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID);
                            int song_id = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID);
                            Songfileinfo info = new Songfileinfo();
                            info.setFile_uri(audioCursor.getString(audiodata));
                            info.setTitle(audioCursor.getString(audioTitle));
                            info.setDuration((audioCursor.getString(audioduration)));
                            info.setArtist(audioCursor.getString(audioartist));
                            info.setAlbum(audioCursor.getString(audioalbum));
                            info.setId(audioCursor.getLong(song_id));
                            info.setAlbum_art((ContentUris.withAppendedId(albumArtUri, audioCursor.getLong(audioalbumid))).toString());
                            songList.add(info);
                        } while (audioCursor.moveToNext());
                    }
                }
                assert audioCursor != null;
                audioCursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return songList;
    }
}
