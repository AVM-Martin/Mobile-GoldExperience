package id.my.avmmartin.goldexperience.activity.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;

import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.data.model.Plan;
import id.my.avmmartin.goldexperience.utils.Helper;

class PlanListViewHolder extends RecyclerView.ViewHolder {
    private TextView tvName;
    private TextView tvDatetime;
    private TextView tvNote;
    private Button btnDelete;

    private Context context;
    private PlanListListener listener;

    PlanListViewHolder(@NonNull View itemView, Context context, PlanListListener listener) {
        super(itemView);

        this.context = context;
        this.listener = listener;
        initComponents();
        setEvents();
    }

    private void initComponents() {
        tvName = itemView.findViewById(R.id.adapter_planlist_tv_name);
        tvDatetime = itemView.findViewById(R.id.adapter_planlist_tv_datetime);
        tvNote = itemView.findViewById(R.id.adapter_planlist_tv_note);
        btnDelete = itemView.findViewById(R.id.adapter_planlist_btn_delete);
    }

    void loadData() {
        tvName.setText(data.getName());
        tvDatetime.setText(Helper.toDateFormat(data.getDate()) + " " + Helper.toTimeFormat(data.getTime()));
        tvNote.setText(data.getNote());

        if (data.getDate().before(Calendar.getInstance())) {
             itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.light_red));
        }
    }

    private void setEvents() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.btnDeleteOnClick(data);
            }
        });
    }

    private Plan data;

    void bindData(Plan data) {
        this.data = data;
    }
}
