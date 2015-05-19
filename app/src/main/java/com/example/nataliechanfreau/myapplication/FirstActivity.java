package com.example.nataliechanfreau.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class FirstActivity extends ActionBarActivity {
    private static final int MAX_SECTIONS = 7;
    private static final int PROPORTION_AMT_ID = 100;
    private static final int GRADE_AMT_ID = 200;
    private static final float ROW_TEXT_SIZE = 25;
    private static final float TITLE_TEXT_SIZE = 20;
    private static final String SAMPLE_PROPORTION = "20";
    private static final String SAMPLE_GRADE = "88";

    private int myNumRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
    }

    public void buttonOnClick(View v) {
        double[] result = doMathOnUserInput();

        double currentPercent = result[0];
        double remainingProportion = result[1];

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(Constants.CURRENT_PERCENT_MESSAGE, currentPercent);
        intent.putExtra(Constants.REMAINING_PROPORTION_MESSAGE, remainingProportion);
        startActivity(intent);
    }

    private double[] doMathOnUserInput() {
        double[] ret = new double[2];

        double currentPercent = 0;
        double remainingProportion = 100;

        for (int i = 1; i <= myNumRows; i++) {
            double proportion = Double.parseDouble(((EditText) findViewById(PROPORTION_AMT_ID + i)).
                    getText().toString());
            double grade = Double.parseDouble(((EditText) findViewById(GRADE_AMT_ID + i)).
                    getText().toString());

            remainingProportion -= proportion;
            currentPercent += 0.01 * proportion * grade;

        }

        ret[0] = currentPercent;
        ret[1] = remainingProportion;

        return ret;
    }

    public void classesButtonOnClick(View v) {
        clearRows();
        addTitleRow();
        calculateNumRows();
        addSectionRows(myNumRows);
    }

    private void clearRows() {
        ((LinearLayout) findViewById(R.id.linearLayout1)).removeAllViews();
    }

    private void addTitleRow() {
        LinearLayout titleRow = createTitleRow();
        ((LinearLayout) findViewById(R.id.linearLayout1)).addView(titleRow);
    }

    private LinearLayout createTitleRow() {
        LinearLayout row = createLinearLayout();
        String title = Constants.SECTION_STRING + "\t" + Constants.PROPORTION_STRING +
                "\t" + Constants.GRADE_STRING;
        TextView titleView = makeSimpleTextView(title, TITLE_TEXT_SIZE);
        addToViewGroup(row, titleView);

        return row;
    }

    private void calculateNumRows() {
        EditText classesText = (EditText) this.findViewById(R.id.classesText);
        int num = Integer.parseInt(classesText.getText().toString());

        if (num > MAX_SECTIONS) {
            classesText.setText("" + MAX_SECTIONS);
            num = MAX_SECTIONS;
        }

        myNumRows = num;
    }

    private void addSectionRows(int num) {
        for (int i = 1; i <= num; i++) {
            LinearLayout row = createSingleSectionRow(i);
            ((LinearLayout) findViewById(R.id.linearLayout1)).addView(row);
        }
    }

    private LinearLayout createSingleSectionRow(int i) {
        LinearLayout row = createLinearLayout();
        TextView section = makeSimpleTextView(Constants.SECTION_STRING + i, ROW_TEXT_SIZE);
        EditText proportion = makeEditText(PROPORTION_AMT_ID + i, SAMPLE_PROPORTION,
                Constants.DECIMAL_INPUT_TYPE, ROW_TEXT_SIZE);
        TextView percent = makeSimpleTextView(Constants.PERCENT_SYMBOL, ROW_TEXT_SIZE);
        EditText grade = makeEditText(GRADE_AMT_ID + i, SAMPLE_GRADE,
                InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER,
                ROW_TEXT_SIZE);
        TextView percent2 = makeSimpleTextView(Constants.PERCENT_SYMBOL, ROW_TEXT_SIZE);

        addToViewGroup(row, section, proportion, percent, grade, percent2);

        return row;
    }

    private void addToViewGroup(ViewGroup view, View ... children) {
        for (View child : children) {
            view.addView(child);
        }
    }

    private LinearLayout createLinearLayout(){
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        return linearLayout;
    }

    private TextView makeSimpleTextView(String content, float textSize) {
        TextView tv = new TextView(this);
        tv.setText(content);
        tv.setTextSize(textSize);
        return tv;
    }

    private EditText makeEditText(int id, String text, int inputType, float textSize) {
        EditText et = new EditText(this);
        et.setId(id);
        et.setText(text);
        et.setTextSize(textSize);
        et.setInputType(inputType);
        et.setSelectAllOnFocus(true);
        return et;
    }
}
