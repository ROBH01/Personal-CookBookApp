package com.example.cookbook;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;


public class AdsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);
        runAd();
    }

    public void runAd() {
        getWindow().setFormat(PixelFormat.OPAQUE);
        VideoView videoHolder = new VideoView(AdsActivity.this);
        videoHolder.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent mainMenu = new Intent(getBaseContext(), MainMenuActivity.class);
                startActivity(mainMenu);
            }
        });
        videoHolder.setMediaController(new MediaController(AdsActivity.this));
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.firstadd);
        videoHolder.setVideoURI(video);
        setContentView(videoHolder);
        videoHolder.start();
    }
}
