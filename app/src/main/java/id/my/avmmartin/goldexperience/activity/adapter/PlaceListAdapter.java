package id.my.avmmartin.goldexperience.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Vector;

import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.data.model.Place;

public class PlaceListAdapter extends ArrayAdapter<Place> {
    private TextView tvName;
    private TextView tvDesc;

    public PlaceListAdapter(Context context, Vector<Place> resource) {
        super(context, R.layout.adapter_place_list, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Place place = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.adapter_place_list, null);
        tvName = view.findViewById(R.id.adapter_placelist_tv_name);
        tvDesc = view.findViewById(R.id.adapter_placelist_tv_desc);

        tvName.setText(place.getName());
        tvDesc.setText(place.getDesc());

        return view;
    }
}
