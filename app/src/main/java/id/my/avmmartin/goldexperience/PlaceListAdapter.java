package id.my.avmmartin.goldexperience;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Vector;

class PlaceListAdapter extends ArrayAdapter<Place> {
    TextView tv_name;
    TextView tv_desc;

    public PlaceListAdapter(Context context, Vector<Place> resource) {
        super(context, R.layout.adapter_place_list, resource);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        Place place = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.adapter_place_list, null);
        tv_name = view.findViewById(R.id.adapter_placelist_tv_name);
        tv_desc = view.findViewById(R.id.adapter_placelist_tv_desc);

        tv_name.setText(place.name);
        tv_desc.setText(place.desc);

        return view;
    }
}
