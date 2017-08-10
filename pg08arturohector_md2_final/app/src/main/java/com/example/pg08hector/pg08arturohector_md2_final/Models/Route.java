package com.example.pg08hector.pg08arturohector_md2_final.Models;

import java.util.ArrayList;

/**
 * Created by pg08hector on 13/12/2016.
 */

public class Route {
    String number;
    String name;
    String direction;
    ArrayList<Bus> busList;

    public Route(String num, String na, String dir, ArrayList<Bus> buses) {
        number = num;
        name = na;
        direction = dir;
        busList = buses;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public ArrayList<Bus> getBusList() {
        return busList;
    }

    public void setBusList(ArrayList<Bus> busList) {
        this.busList = busList;
    }
}
