package com.example.pavel.tappinggame;

import android.content.SharedPreferences;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.pavel.tappinggame.databinding.ActivityMainBinding;

  
public class MainActivity extends AppCompatActivity {


    ActivityMainBinding b;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b= DataBindingUtil.setContentView(this,R.layout.activity_main);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        b.setScore(preferences.getInt("SCORE",0));

        player = MediaPlayer.create(this,R.raw.jalals);
        player.start();

//        player.seekTo(preferences.getInt("position",0));

        b.tapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.setScore(b.getScore() +1);
            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("SCORE",b.getScore());
        editor.putInt("position",player.getCurrentPosition());

        editor.apply();


    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        player.seekTo(sharedPreferences.getInt("position",0));

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        player.seekTo(sharedPreferences.getInt("position",0));
    }
}
