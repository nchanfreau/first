package com.example.nataliechanfreau.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class FirstActivity extends ActionBarActivity {
    public static final String EXTRA_MESSAGE = "com.example.nataliechanfreau.myapplication.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        //createAdderButton();
        letsTrySomething(5);
    }

    private void letsTrySomething(int num) {
        for (int i = 1; i <= num; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            Button btn = new Button(this);
            btn.setId(i);
            final int id_ = btn.getId();
            btn.setText("button " + id_);
            ((LinearLayout) findViewById(R.id.linearLayout1)).addView(btn, params);
            Button btn1 = ((Button) findViewById(id_));
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                            "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }
    }

    private void createHeaderRow() {

    }

    private void createRow() {
        Button b = new Button(this);
        b.setText("here's the row");
        RelativeLayout ll = (RelativeLayout)findViewById(R.id.main_relative);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        ll.addView(b, lp);
    }

    private void createAdderButton() {
        Button b = new Button(this);
        b.setText("Add new class");
        b.setOnClickListener(getAdderClickListener());
        RelativeLayout ll = (RelativeLayout)findViewById(R.id.main_relative);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        ll.addView(b, lp);
    }

    private View.OnClickListener getAdderClickListener() {
        View.OnClickListener ocl = new View.OnClickListener() {
            public void onClick(View v) {
                createRow();
            }
        };
        return ocl;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void buttonOnClick(View v) {
        Button b = (Button) v;
        //b.setText("clicked!");
        //this.findViewById(R.id.natalie_layout).setBackgroundColor(Color.RED);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "teehee");
        startActivity(intent);
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

}
