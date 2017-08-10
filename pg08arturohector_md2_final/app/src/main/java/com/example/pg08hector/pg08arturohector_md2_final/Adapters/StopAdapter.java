package com.example.pg08hector.pg08arturohector_md2_final.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.pg08hector.pg08arturohector_md2_final.DataManagement.DataAccess;
import com.example.pg08hector.pg08arturohector_md2_final.R;

import java.util.ArrayList;

/**
 * Created by pg08hector on 13/12/2016.
 */

public class StopAdapter extends ArrayAdapter<String> {
    private Context _context;
    private ArrayList<String> _stopList = new ArrayList<>();
    private DataAccess _dataAccess;

    public StopAdapter(Context context, DataAccess da, ArrayList<String> stops) {
        super(context, R.layout.item_stop, stops);
        this._stopList = stops;
        this._context = context;
        this._dataAccess = da;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String currentStop = this.getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_stop, parent, false);
        }

        TextView nameTextView = (TextView) convertView.findViewById(R.id.stop);
        nameTextView.setText(currentStop);
        Button deleteButton = (Button)convertView.findViewById(R.id.remove_button);
        deleteButton.setTag(position);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                Integer index = (Integer) v.getTag();
                String stopNumber = _stopList.get(index.intValue());
                _dataAccess.openWrite();
                _dataAccess.DeleteStop(stopNumber);
                _dataAccess.close();
                _stopList.remove(index.intValue()); //or some other task
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
