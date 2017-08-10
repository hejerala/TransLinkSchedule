package com.example.pg08hector.pg08arturohector_md2_final.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.pg08hector.pg08arturohector_md2_final.Models.Bus;
import com.example.pg08hector.pg08arturohector_md2_final.Models.Route;
import com.example.pg08hector.pg08arturohector_md2_final.R;

import java.util.ArrayList;

/**
 * Created by pg08hector on 13/12/2016.
 */

public class RouteAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private ArrayList<Route> _routeList; // header titles
    // child data in format of header title, child title
    //private HashMap<Route, ArrayList<Bus>> _listDataChild;

    public RouteAdapter(Context context, ArrayList<Route> routeList) {
        this._context = context;
        this._routeList = routeList;
        //this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition);
        return this._routeList.get(groupPosition).getBusList().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final Bus childBus = (Bus) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_route, null);
        }

        TextView destinationTxtListChild = (TextView) convertView.findViewById(R.id.bus_destination);
        TextView timeTxtListChild = (TextView) convertView.findViewById(R.id.bus_time);

        destinationTxtListChild.setText(childBus.getDestination());
        timeTxtListChild.setText(childBus.getExpectedTime());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
        return this._routeList.get(groupPosition).getBusList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._routeList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._routeList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Route headerRoute = (Route) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.header_route, null);
        }

        TextView routeHeader = (TextView) convertView.findViewById(R.id.route_header);
        routeHeader.setTypeface(null, Typeface.BOLD);
        routeHeader.setText(headerRoute.getNumber()+": "+headerRoute.getName());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
