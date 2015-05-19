package com.example.nataliechanfreau.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;


public class SecondActivity extends ActionBarActivity {
    private double myCurrentPercent;
    private double myRemainingProportion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        interpretMessage();
        initializeSeekBar();
    }

    private void interpretMessage() {
        String message = (String) getIntent().getExtras().get(FirstActivity.EXTRA_MESSAGE);
        String[] percentages = message.split(FirstActivity.DELIMITER);
        myCurrentPercent = Double.parseDouble(percentages[0]);
        myRemainingProportion = Double.parseDouble(percentages[1]);
    }

    public void onButtonClick(View view) {
        this.finish();
    }

    private void initializeSeekBar() {
        final SeekBar sk = (SeekBar) findViewById(R.id.seekBar);
        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView gradeGoal = (TextView) findViewById(R.id.gradeGoal);
                gradeGoal.setText(Integer.toString(seekBar.getProgress()));
                setGradeRequired();
            }
        });
        setGradeRequired();
    }

    private void setGradeRequired() {
        TextView gradeRequired = (TextView) findViewById(R.id.gradeRequired);

        

    }
}
