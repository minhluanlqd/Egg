package com.example.egg;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textViewTimes;
    SeekBar seekBarTimes;
    Boolean counterIsActive = false;
    Button buttonTimes;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBarTimes = findViewById(R.id.seekBarTimes);
        buttonTimes = findViewById(R.id.buttonTimes);
        textViewTimes = findViewById(R.id.textViewTimes);


        seekBarTimes.setMax(600);
        seekBarTimes.setProgress(30);

        seekBarTimes.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void ButtonClicked(View view) {

        if (counterIsActive){
            resetTimer();
        }else {
            counterIsActive = true;
            seekBarTimes.setEnabled(false);
            buttonTimes.setText("STOP!");

            countDownTimer = new CountDownTimer(seekBarTimes.getProgress() * 1000 +100 ,1000){

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) (millisUntilFinished/1000));
                }

                @Override
                public void onFinish() {
                    //add some effect in here
                    resetTimer();
                }
            }.start();
        }
    }
    public void updateTimer(int secondLeft){
        int minute = secondLeft/60;
        int second = secondLeft - (minute*60);

        String secString = Integer.toString(second);
        if (second <= 9){
            secString = "0" + secString;
        }
        textViewTimes.setText(Integer.toString(minute) + ":" + secString);

    }
    public void resetTimer(){
        textViewTimes.setText("0:30");
        seekBarTimes.setProgress(30);
        seekBarTimes.setEnabled(true);
        countDownTimer.cancel();
        buttonTimes.setText("START");
        counterIsActive= false;
    }
}
