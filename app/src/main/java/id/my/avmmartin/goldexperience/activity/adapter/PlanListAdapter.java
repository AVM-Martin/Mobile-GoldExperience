package id.my.avmmartin.goldexperience.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.Date;
import java.util.Vector;

import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.data.model.Plan;
import id.my.avmmartin.goldexperience.utils.Helper;

public class PlanListAdapter extends ArrayAdapter<Plan> {
    private TextView tvName;
    private TextView tvDatetime;
    private TextView tvNote;
    private Button btnDelete;

    public PlanListAdapter(Context context, Vector<Plan> resource) {
        super(context, R.layout.adapter_plan_list, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Plan plan = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.adapter_plan_list, null);
        tvName = view.findViewById(R.id.adapter_planlist_tv_name);
        tvDatetime = view.findViewById(R.id.adapter_planlist_tv_datetime);
        tvNote = view.findViewById(R.id.adapter_planlist_tv_note);
        btnDelete = view.findViewById(R.id.adapter_planlist_btn_delete);

        tvName.setText(plan.getName());
        tvDatetime.setText(Helper.toDateFormat(plan.getDate()) + " " + Helper.toTimeFormat(plan.getTime()));
        tvNote.setText(plan.getNote());
        btnDelete.setTag(plan);

        if (plan.getDate().before(new Date())) {
            view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_red));
        }

        return view;
    }
}
