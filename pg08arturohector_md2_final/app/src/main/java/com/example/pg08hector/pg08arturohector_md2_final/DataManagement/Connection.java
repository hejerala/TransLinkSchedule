package com.example.pg08hector.pg08arturohector_md2_final.DataManagement;

import java.util.Objects;

/**
 * Created by pg08hector on 13/12/2016.
 */

public class Connection {
    private String URL;
    private String Parameters;

    public Connection(String url, Object parameters)
    {
        this.URL = url;
        this.Parameters = parameters.toString();
    }

    public Connection(String url)
    {
        this.URL = url;
        this.Parameters = "";
    }

    public String getURL()
    {
        return URL;
    }

    public String getParameters()
    {
        return Parameters;
    }
}
