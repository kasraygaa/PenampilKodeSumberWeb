package com.example.android.penampilkodesumberweb;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //about Spinner
    //define spinner
    Spinner sp;
    //make string array
    String names[] = {"http://", "https://"};
    //define array adapter of string type
    ArrayAdapter<String> adapter;
    //define string var for record
    String record = "";

    //about Get Source Code
    ConnectInternet c1;
    static TextView myText;
    ConnectivityManager myConnManager;
    NetworkInfo myInfo;
    EditText getURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //about Spinner
        sp = (Spinner) findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        //set adapter to spinner
        sp.setAdapter(adapter);

        //set spinner method
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                //use position vallue
                switch(position){
                    case 0:
                        record = "http://";
                        break;
                    case 1:
                        record = "https://";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        //about Get Source Code
        myText = (TextView) findViewById(R.id.result);
        myConnManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        myInfo = myConnManager.getActiveNetworkInfo();
    }

    //set display button click to show source code
    public void doS(View view) {
        c1 = new ConnectInternet(this);
        getURL = (EditText) findViewById(R.id.indeks);
        if(getURL.getText().toString().equals(""))
            Toast.makeText(this, "Please Enter The URL", Toast.LENGTH_SHORT).show();
        else if(isNetworkAvailable()){
            Toast.makeText(this, "Wait a minute, it's being processed", Toast.LENGTH_SHORT).show();
            myText.setText("");
            c1.execute(record + getURL.getText().toString());
        }else{
            Toast.makeText(this, "Can not connect, Please check your internet", Toast.LENGTH_SHORT).show();
            myText.setText("");
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
