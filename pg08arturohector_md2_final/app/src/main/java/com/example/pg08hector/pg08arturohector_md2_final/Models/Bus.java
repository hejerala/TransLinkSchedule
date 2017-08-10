package com.example.pg08hector.pg08arturohector_md2_final.Models;

/**
 * Created by pg08hector on 13/12/2016.
 */

public class Bus {
    String destination;
    String expectedTime;

    public Bus(String dest, String time) {
        destination = dest;
        expectedTime = time;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(String expectedTime) {
        this.expectedTime = expectedTime;
    }
}
