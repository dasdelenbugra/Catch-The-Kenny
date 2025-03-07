package com.example.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timeText;
    TextView scoreText;
    int score;

    // TODO: shift + alt + sol click ile imleçleri çoğaltabilirsin
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText = (TextView) findViewById(R.id.timeText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        imageView = findViewById(R.id.imageView);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView4);
        imageView4 = findViewById(R.id.imageView5);
        imageView5 = findViewById(R.id.imageView6);
        imageView6 = findViewById(R.id.imageView7);
        imageView7 = findViewById(R.id.imageView8);
        imageView8 = findViewById(R.id.imageView9);
        imageArray = new ImageView[]{imageView, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8};

        hideImages();

        score = 0;
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
            timeText.setText("Time off");
            handler.removeCallbacks(runnable);
            for (ImageView image : imageArray) {
                image.setVisibility(View.INVISIBLE);
            }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart");
                alert.setMessage("Restart the game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                });
               alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
                   }
               });
               alert.show();
            }
        }.start();

    }

    public void increaseScore(View clickedImageView) {
        score++;
        scoreText.setText("Score: " + score);  // Puanı güncelleme işlemi
    }

    public void hideImages() {
        handler = new Handler(); // ne kadar süreyle çalışmasını istiyorsak
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                Random rnd = new Random();
                int i = rnd.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this, 500);
            }

        };
        handler.post(runnable);
    }

}

