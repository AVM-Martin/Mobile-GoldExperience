package id.my.avmmartin.goldexperience;

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

class PlanListAdapter extends ArrayAdapter<Plan> {
    TextView tv_name;
    TextView tv_datetime;
    TextView tv_note;
    Button btn_delete;

    public PlanListAdapter(Context context, Vector<Plan> resource) {
        super(context, R.layout.adapter_plan_list, resource);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        Plan plan = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.adapter_plan_list, null);
        tv_name = view.findViewById(R.id.adapter_planlist_tv_name);
        tv_datetime = view.findViewById(R.id.adapter_planlist_tv_datetime);
        tv_note = view.findViewById(R.id.adapter_planlist_tv_note);
        btn_delete = view.findViewById(R.id.adapter_planlist_btn_delete);

        tv_name.setText(plan.name);
        tv_datetime.setText(Helper.to_date_format(plan.date) + " " + Helper.to_time_format(plan.time));
        tv_note.setText(plan.note);
        btn_delete.setTag(plan);

        if (plan.date.before(new Date())) {
            view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.lightred));
        }

        return view;
    }
}
