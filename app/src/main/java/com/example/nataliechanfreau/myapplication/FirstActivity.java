package com.example.nataliechanfreau.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class FirstActivity extends ActionBarActivity {
    private static final int MAX_SECTIONS = 7;
    private static final float ROW_TEXT_SIZE = 25;
    private static final float TITLE_TEXT_SIZE = 20;
    private static final String SAMPLE_PROPORTION = "20";
    private static final String SAMPLE_GRADE = "88";

    private static final String PROPORTION_MESSAGE = "proportion";
    private static final String GRADE_MESSAGE = "grade";
    private static final String NUM_ROWS_MESSAGE = "numRows";

    private List<Row> myRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt(NUM_ROWS_MESSAGE, myRows.size());

        for (int i = 0; i < myRows.size(); i++) {
            savedInstanceState.putString(PROPORTION_MESSAGE + i,
                    myRows.get(i).getProportionString());
            savedInstanceState.putString(GRADE_MESSAGE + i,
                    myRows.get(i).getGradeString());
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int numRows = savedInstanceState.getInt(NUM_ROWS_MESSAGE);

        EditText classesText = (EditText) this.findViewById(R.id.classesText);
        classesText.setText(String.valueOf(numRows));

        if (numRows > 0) {
            myRows = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                String proportionString = savedInstanceState.getString(PROPORTION_MESSAGE + i);
                String gradeString = savedInstanceState.getString(GRADE_MESSAGE + i);
                EditText proportion = ViewMaker.makeEditText(proportionString,
                        Constants.DECIMAL_INPUT_TYPE, ROW_TEXT_SIZE, this);
                EditText grade = ViewMaker.makeEditText(gradeString,
                        Constants.DECIMAL_INPUT_TYPE, ROW_TEXT_SIZE, this);
                myRows.add(new Row(proportion, grade));
            }
        }

        addContent();
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

        for (int i = 0; i < myRows.size(); i++) {
            double proportion = myRows.get(i).getProportion();
            double grade = myRows.get(i).getGrade();
            remainingProportion -= proportion;
            currentPercent += 0.01 * proportion * grade;
        }

        ret[0] = currentPercent;
        ret[1] = remainingProportion;

        return ret;
    }

    public void classesButtonOnClick(View v) {
        clearRows();
        addContent();
    }

    private void addContent() {
        addTitleRow();
        addSectionRows(calculateNumRows());
    }

    private void clearRows() {
        ((LinearLayout) findViewById(R.id.linearLayout1)).removeAllViews();
        myRows = null;
    }

    private void addTitleRow() {
        LinearLayout titleRow = createTitleRow();
        ((LinearLayout) findViewById(R.id.linearLayout1)).addView(titleRow);
    }

    private LinearLayout createTitleRow() {
        LinearLayout row = ViewMaker.createLinearLayout(this);
        String title = Constants.SECTION_STRING + Constants.TAB_SYMBOL + Constants.PROPORTION_STRING +
                Constants.TAB_SYMBOL + Constants.GRADE_STRING;
        TextView titleView = ViewMaker.makeSimpleTextView(title, TITLE_TEXT_SIZE, this);
        ViewMaker.addToViewGroup(row, titleView);

        return row;
    }

    private int calculateNumRows() {
        EditText classesText = (EditText) this.findViewById(R.id.classesText);

        try {
            int num = Integer.parseInt(classesText.getText().toString());

            if (num > MAX_SECTIONS) {
                classesText.setText(String.valueOf(MAX_SECTIONS));
                num = MAX_SECTIONS;
            }

            return num;

        } catch (NumberFormatException e) {
            return 0;
        }

    }

    private void addSectionRows(int num) {
        if (myRows == null) {
            createDefaultRows(num);
        }
        for (int i = 1; i <= num; i++) {
            LinearLayout row = createSingleSectionRow(i);
            ((LinearLayout) findViewById(R.id.linearLayout1)).addView(row);
        }
    }

    private void createDefaultRows(int numRows) {
        myRows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            EditText proportion = ViewMaker.makeEditText(SAMPLE_PROPORTION,
                    Constants.DECIMAL_INPUT_TYPE, ROW_TEXT_SIZE, this);
            EditText grade = ViewMaker.makeEditText(SAMPLE_GRADE,
                    Constants.DECIMAL_INPUT_TYPE, ROW_TEXT_SIZE, this);
            myRows.add(new Row(proportion, grade));
        }
    }

    private LinearLayout createSingleSectionRow(int i) {
        LinearLayout row = ViewMaker.createLinearLayout(this);
        TextView section = ViewMaker.makeSimpleTextView(Constants.SECTION_STRING + i, ROW_TEXT_SIZE,
                this);
        EditText proportion = myRows.get(i - 1).getProportionView();
        TextView percent = ViewMaker.makeSimpleTextView(Constants.PERCENT_SYMBOL, ROW_TEXT_SIZE,
                this);
        EditText grade = myRows.get(i - 1).getGradeView();
        TextView percent2 = ViewMaker.makeSimpleTextView(Constants.PERCENT_SYMBOL, ROW_TEXT_SIZE,
                this);

        ViewMaker.addToViewGroup(row, section, proportion, percent, grade, percent2);

        return row;
    }

}
