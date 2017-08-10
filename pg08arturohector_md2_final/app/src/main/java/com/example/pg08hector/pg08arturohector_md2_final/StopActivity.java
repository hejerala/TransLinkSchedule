package com.example.pg08hector.pg08arturohector_md2_final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.pg08hector.pg08arturohector_md2_final.Adapters.RouteAdapter;
import com.example.pg08hector.pg08arturohector_md2_final.Adapters.StopAdapter;
import com.example.pg08hector.pg08arturohector_md2_final.DataManagement.AsyncResponse;
import com.example.pg08hector.pg08arturohector_md2_final.DataManagement.Connection;
import com.example.pg08hector.pg08arturohector_md2_final.DataManagement.ConnectionBuilder;
import com.example.pg08hector.pg08arturohector_md2_final.DataManagement.ConnectionManager;
import com.example.pg08hector.pg08arturohector_md2_final.Models.Bus;
import com.example.pg08hector.pg08arturohector_md2_final.Models.Route;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class StopActivity extends AppCompatActivity implements AsyncResponse {
    String stop;
    StopActivity context = this;
    ExpandableListView routeListView;
    EditText busEditText;
    EditText timeEditText;
    Button searchButton;
    ArrayList<Route> routeList;
    RouteAdapter myRouteAdapter;
    private ConnectionManager mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);

        stop = getIntent().getStringExtra("STOP");

        routeListView = (ExpandableListView) findViewById(R.id.routeListView);
        busEditText = (EditText) findViewById(R.id.busEditText);
        timeEditText = (EditText) findViewById(R.id.timeEditText);
        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAuthTask != null)
                {
                    return;
                }
                try
                {
                    Connection connection = ConnectionBuilder.BuildStopEstimateConnection(stop, busEditText.getText().toString(), timeEditText.getText().toString());
                    mAuthTask = new ConnectionManager(context);
                    mAuthTask.execute(connection.getURL());
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
        routeList = new ArrayList<>();
        myRouteAdapter = new RouteAdapter(this, routeList);
        routeListView.setAdapter(myRouteAdapter);
    }

    //this override the implemented method from asyncTask
    @Override
    public void processFinish(String output){
        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.
        try{
            JSONArray routesArray = new JSONArray(output);
            for (int i = 0; i < routesArray.length(); i++) {
                JSONObject routeRow = routesArray.getJSONObject(i);
                ArrayList<Bus> busList = new ArrayList<>();
                String number = routeRow.getString("RouteNo");
                String name = routeRow.getString("RouteName");
                String direction = routeRow.getString("Direction");
                JSONArray busesArray = routeRow.getJSONArray("Schedules");
                for (int j = 0; j < busesArray.length(); j++) {
                    JSONObject busRow = busesArray.getJSONObject(j);
                    String destination = busRow.getString("Destination");
                    String time = busRow.getString("ExpectedLeaveTime");
                    Bus tmpBus = new Bus(destination, time);
                    busList.add(tmpBus);
                }
                Route tmpRoute = new Route(number, name, direction, busList);
                routeList.add(tmpRoute);
            }
            myRouteAdapter.notifyDataSetChanged();
        }catch (JSONException e){
            e.printStackTrace();
        }catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
