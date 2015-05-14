package com.example.nataliechanfreau.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


public class FirstActivity extends ActionBarActivity {
    public static final String EXTRA_MESSAGE = "com.example.nataliechanfreau.myapplication.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createHeaderRow();
        createFirstRow();
        setContentView(R.layout.activity_one);
        createAdderButton();
    }

    private void createHeaderRow() {

    }

    private void createFirstRow() {

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
        /*View.OnClickListener ocl = new View.OnClickListener() {
            public void onClick(View v) {

            }
        };
        return ocl;*/
        return e -> lol();
    }

    private void lol() {

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
