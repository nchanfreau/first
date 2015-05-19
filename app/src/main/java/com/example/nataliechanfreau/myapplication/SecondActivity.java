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
        myCurrentPercent = (double) getIntent().getExtras().get(FirstActivity.CURRENT_PERCENT_MESSAGE);
        myRemainingProportion = (double) getIntent().getExtras().get(FirstActivity.REMAINING_PROPORTION_MESSAGE);
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
                setGradeRequired(progress);
            }
        });
        setGradeRequired(sk.getProgress());
    }

    private void setGradeRequired(int gradeGoal) {
        double gradeRequired = (gradeGoal - myCurrentPercent) / myRemainingProportion * 100.0;
        gradeRequired = gradeRequired < 0 ? 0 : gradeRequired;
        //gradeRequired = ((int) (gradeRequired * 100)) / 100.0;
        TextView gradeRequiredTextView = (TextView) findViewById(R.id.gradeRequired);
        gradeRequiredTextView.setText(gradeRequired + "%");
    }
}
