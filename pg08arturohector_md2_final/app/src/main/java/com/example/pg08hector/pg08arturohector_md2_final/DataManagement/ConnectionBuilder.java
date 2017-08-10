package com.example.pg08hector.pg08arturohector_md2_final.DataManagement;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pg08hector on 13/12/2016.
 */

public class ConnectionBuilder {
    private static final String URL_BASE = "http://api.translink.ca/rttiapi/v1";
    //private static final String URL_BASE = "http://10.1.1.7:8067";
    private static final String URL_STOPS = "/stops";
    private static final String URL_STOPS_ESTIMATES = "/estimates";
    private static final String API_KEY = "hoO0tPQc97DwYZ65M6qq";
    //private static final String URL_UPDATE_CLIENTE_DETAIL = "/Mobile/UpdateUserInfo";


    public static Connection BuildStopEstimateConnection(String stop, String count, String timeFrame) throws JSONException
    {
        Connection connection = new Connection(URL_BASE+URL_STOPS+"/"+stop+URL_STOPS_ESTIMATES+"?apikey="+API_KEY+"&count="+count+"&timeframe="+timeFrame);

        return connection;
    }
}
