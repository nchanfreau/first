package com.example.nataliechanfreau.myapplication;

import android.widget.EditText;

/**
 * Created by nataliechanfreau on 5/19/15.
 */
public class Row {
    private EditText myProportion;
    private EditText myGrade;

    public Row(EditText proportion, EditText grade) {
        myProportion = proportion;
        myGrade = grade;
    }

    public EditText getProportionView() {
        return myProportion;
    }

    public EditText getGradeView() {
        return myGrade;
    }

    public String getProportionString() {
        return getStringValue(myProportion);
    }

    public String getGradeString() {
        return getStringValue(myGrade);
    }

    public double getProportion() {
        return getDoubleValue(myProportion);
    }

    public double getGrade() {
        return getDoubleValue(myGrade);
    }

    private double getDoubleValue(EditText et) {
        return Double.parseDouble(getStringValue(et));
    }

    private String getStringValue(EditText et) {
        return et.getText().toString();
    }

}