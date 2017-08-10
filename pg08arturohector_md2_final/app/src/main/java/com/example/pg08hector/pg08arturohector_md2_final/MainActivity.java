package com.example.pg08hector.pg08arturohector_md2_final;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.pg08hector.pg08arturohector_md2_final.Adapters.StopAdapter;
import com.example.pg08hector.pg08arturohector_md2_final.DataManagement.AsyncResponse;
import com.example.pg08hector.pg08arturohector_md2_final.DataManagement.Connection;
import com.example.pg08hector.pg08arturohector_md2_final.DataManagement.ConnectionBuilder;
import com.example.pg08hector.pg08arturohector_md2_final.DataManagement.ConnectionManager;
import com.example.pg08hector.pg08arturohector_md2_final.DataManagement.DataAccess;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView stopListView;
    EditText stopEditText;
    Button addStopButton;
    ArrayList<String> stopList;
    StopAdapter myStopAdapter;

    private DataAccess dataAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataAccess = new DataAccess(MainActivity.this);
        dataAccess.openWrite();
        stopList = dataAccess.GetAllStops();
        dataAccess.close();

        stopListView = (ListView) findViewById(R.id.stopListView);
        stopEditText = (EditText) findViewById(R.id.stopEditText);
        addStopButton = (Button) findViewById(R.id.addStopButton);
        addStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmpStop = stopEditText.getText().toString();
                stopList.add(tmpStop);
                myStopAdapter.notifyDataSetChanged();
                dataAccess.openWrite();
                dataAccess.InsertStop(tmpStop);
                dataAccess.close();
            }
        });
        myStopAdapter = new StopAdapter(this, dataAccess, stopList);
        stopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String tmpStop = (String) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getBaseContext(), StopActivity.class);
                intent.putExtra("STOP", tmpStop);
                startActivity(intent);
                //Snackbar.make(view, "stop: "+tmpStop, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        stopListView.setAdapter(myStopAdapter);
    }

}
