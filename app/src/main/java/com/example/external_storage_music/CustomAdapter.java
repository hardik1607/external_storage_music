package com.example.external_storage_music;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    MainActivity mainActivity;
    ArrayList<Songfileinfo> albumarr;

    public CustomAdapter(MainActivity context, ArrayList albumarr) {
        this.mainActivity=context;
        this.albumarr=albumarr;
    }



    @Override
    public int getCount() {
        return albumarr.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mainActivity);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.custom_album_child, parent, false);
        }
        Songfileinfo songfileinfo = albumarr.get(position);
        TextView textView = convertView.findViewById(R.id.textView12);
        TextView textView1 = convertView.findViewById(R.id.albumchild_sub);
        TextView textView2 = convertView.findViewById(R.id.albumchild_sub1);

        Button button = convertView.findViewById(R.id.push);
        ImageView imageView = convertView.findViewById(R.id.image);
        MediaPlayer mp = new MediaPlayer();
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(songfileinfo.getFile_uri());

        byte [] data = mmr.getEmbeddedPicture();
        //coverart is an Imageview object

        // convert the byte array to a bitmap
        if(data != null)
        {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            imageView.setImageBitmap(bitmap); //associated cover art in bitmap
        }
        else
        {
            imageView.setImageResource(R.drawable.ic_launcher_background); //any default cover resourse folder
        }

        imageView.setAdjustViewBounds(true);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(500, 500));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(songfileinfo.getFile_uri());
                if (button.getText().equals("play"))
                {
                    button.setText("pause");
                    mp.stop();
                    mp.reset();
                    try {
                        mp.setDataSource(songfileinfo.getFile_uri());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
//                    mp.prepareAsync();
                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            // Do something. For example: playButton.setEnabled(true);
                           mp.start();
                        }
                    });
                  mp.prepareAsync();
                }
                else
                {
                    button.setText("play");
                    if (mp.isPlaying())
                    {
                        mp.stop();
                        mp.reset();

                    }
                }

            }
        });


        if (songfileinfo != null) {
            textView.setText(songfileinfo.getTitle());
//            textView.setTypeface(typeface);
        }
        if (songfileinfo != null) {
            textView1.setText(songfileinfo.getArtist());
//            textView1.setTypeface(typeface);
        }
        long timer = 0;

        if (songfileinfo != null) {
            timer = Long.parseLong(songfileinfo.getDuration());
            textView2.setText(""+milliSecondsToTimer(timer));
//            textView2.setTypeface(typeface);
        }

        return convertView;
    }
    public String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

// Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
// Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

// Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

// return timer string
        return finalTimerString;
    }

}