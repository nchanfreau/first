package com.example.nataliechanfreau.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by nataliechanfreau on 5/19/15.
 */
public class ViewMaker {
    public static EditText makeEditText(String text, int inputType, float textSize, Context c) {
        EditText et = new EditText(c);
        et.setText(text);
        et.setTextSize(textSize);
        et.setInputType(inputType);
        et.setSelectAllOnFocus(true);
        return et;
    }

    public static LinearLayout createLinearLayout(Context c){
        LinearLayout linearLayout = new LinearLayout(c);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        return linearLayout;
    }

    public static TextView makeSimpleTextView(String content, float textSize, Context c) {
        TextView tv = new TextView(c);
        tv.setText(content);
        tv.setTextSize(textSize);
        return tv;
    }

    public static void addToViewGroup(ViewGroup view, View... children) {
        for (View child : children) {
            view.addView(child);
        }
    }

}
