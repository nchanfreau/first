package com.example.nataliechanfreau.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

/**
 * Created by nataliechanfreau on 5/14/15.
 */
public class CustomNumberPicker extends NumberPicker {

    public CustomNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        //setMinValue(0);
        //setMaxValue(7);
        setValue(4);

        String[] values = new String[8];
        for(int i=0; i < values.length; i++) {
            values[i] = Integer.toString(i);
        }

        setDisplayedValues(values);
        setWrapSelectorWheel(false);
    }
}