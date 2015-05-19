package com.example.nataliechanfreau.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class FirstActivity extends ActionBarActivity {
    public static final String EXTRA_MESSAGE = "com.example.nataliechanfreau.myapplication.MESSAGE";
    private static final int MAX_SECTIONS = 6;
    private static final int PROPORTION_AMT = 100;
    private static final int GRADE_AMT = 200;
    private static final float ROW_TEXT_SIZE = 25;
    private static final String SECTION_STRING = "Section ";
    private static final String PERCENT_SYMBOL = "%";
    private static final String SAMPLE_PROPORTION = "20";
    private static final String SAMPLE_GRADE = "88";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    public void buttonOnClick(View v) {
        Button b = (Button) v;
        //b.setText("clicked!");
        //this.findViewById(R.id.natalie_layout).setBackgroundColor(Color.RED);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "teehee");
        startActivity(intent);
    }

    public void classesButtonOnClick(View v ) {
        EditText classesText = (EditText) this.findViewById(R.id.classesText);
        int num = Integer.parseInt(classesText.getText().toString());

        if (num > MAX_SECTIONS) {
            classesText.setText("" + MAX_SECTIONS);
            num = MAX_SECTIONS;
        }
        ((LinearLayout) findViewById(R.id.linearLayout1)).removeAllViews();
        for (int i = 1; i <= num; i++) {
            LinearLayout row = createLinearLayout();

            TextView section = makeSimpleTextView(SECTION_STRING + i, ROW_TEXT_SIZE);
            EditText proportion = makeEditText(PROPORTION_AMT + i, SAMPLE_PROPORTION,
                    InputType.TYPE_CLASS_NUMBER, ROW_TEXT_SIZE);
            TextView percent = makeSimpleTextView(PERCENT_SYMBOL, ROW_TEXT_SIZE);
            EditText grade = makeEditText(GRADE_AMT + i, SAMPLE_GRADE,
                    InputType.TYPE_CLASS_NUMBER, ROW_TEXT_SIZE);
            TextView percent2 = makeSimpleTextView(PERCENT_SYMBOL, ROW_TEXT_SIZE);

            //final int id_ = btn.getId();
            //((LinearLayout) findViewById(R.id.linearLayout1)).addView(tv);

            row.addView(section);
            row.addView(proportion);
            row.addView(percent);
            row.addView(grade);
            row.addView(percent2);

            ((LinearLayout) findViewById(R.id.linearLayout1)).addView(row);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
