package com.example.nataliechanfreau.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SecondActivity extends ActionBarActivity {
    private static final int NUM_DECIMALS_TO_ROUND = 2;
    private static final double DELTA = 1.0/10000.0;

    private double myCurrentPercent;
    private double myRemainingProportion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        if (interpretAndVerifyMessage()) {
            initializeSeekBar();
        } else {
            endActivity();
        }
    }

    private boolean interpretAndVerifyMessage() {
        myCurrentPercent = (double) getIntent().getExtras().get(Constants.CURRENT_PERCENT_MESSAGE);
        myRemainingProportion = (double) getIntent().getExtras().get(Constants.REMAINING_PROPORTION_MESSAGE);

        return !(myRemainingProportion <= DELTA || myCurrentPercent >= 100.0 - DELTA);
    }

    private void endActivity() {
        this.finish();
    }

    public void onButtonClick(View view) {
        endActivity();
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
        double gradeRequired = calculateGradeRequired(gradeGoal);
        TextView gradeRequiredTextView = (TextView) findViewById(R.id.gradeRequired);
        gradeRequiredTextView.setText(gradeRequired + Constants.PERCENT_SYMBOL);
    }

    private double calculateGradeRequired(int gradeGoal) {
        double gradeRequired = (gradeGoal - myCurrentPercent) / myRemainingProportion * 100.0;
        gradeRequired = gradeRequired < 0 ? 0 : gradeRequired;

        BigDecimal tempGradeRequired = BigDecimal.valueOf(gradeRequired).
                setScale(NUM_DECIMALS_TO_ROUND, RoundingMode.HALF_UP);
        double roundedGradeRequired = tempGradeRequired.doubleValue();

        return roundedGradeRequired;
    }
}
