package com.example.eggtimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerTextview;
    SeekBar timerSeekBar;
    boolean counterIsActive=false;
    Button goButton;
    CountDownTimer countDownTimer;
    public void resetTimer(){
        timerTextview.setText("0:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("GO!");
        counterIsActive = false;
    }

    public void buttonClicked(View view){

        if (counterIsActive){
            resetTimer();

        }else {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            goButton.setText("STOP!!!!");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.hello);
                    mplayer.start();
                    resetTimer();
                }
            }.start();
        }
    }
    public void updateTimer(int secondsLeft){
        int min= secondsLeft/60;
        int seconds= secondsLeft - (min*60);
        String secondString=Integer.toString(seconds);
        if(secondString.equals("0")){
            secondString="00";
        }
        if(seconds<=9){
            secondString="0"+secondString;
        }
        timerTextview.setText(Integer.toString(min)+":"+Integer.toString(seconds));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar=findViewById(R.id.timerSeekBar);
        timerTextview=findViewById(R.id.countdownTextView);
        goButton=findViewById(R.id.go);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30); //Starting position of seekbar
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}