package com.self.quickcall;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.self.quickcall.data.PhoneNumberMap;


public class QuickCallActivity extends Activity {

    private static final String TAG = "QuickCallApp";
    Spinner myNames;
    TextView showPhoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_call);

        myNames = (Spinner)findViewById(R.id.my_names);
        myNames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showPhoneNumber = (TextView) findViewById(R.id.phone_number);
                String selectedName = (String) parent.getItemAtPosition(position);
                String selectedPhoneNumber = (String) PhoneNumberMap.numberMap.get(selectedName);
                showPhoneNumber.setText(selectedPhoneNumber);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothin
            }
        });
    }

    public void clickToCall(View view){
        showPhoneNumber = (TextView) findViewById(R.id.phone_number);
        String callString = (String)showPhoneNumber.getText();
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + callString));
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quick_call, menu);
        return true;
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
