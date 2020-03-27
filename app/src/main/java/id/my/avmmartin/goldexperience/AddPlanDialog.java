package id.my.avmmartin.goldexperience;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

final class AddPlanDialog extends DialogFragment {
    public interface Listener {
        void btn_submit_onclick(AddPlanDialog dialog);
    }

    private AddPlanDialog.Listener listener;
    TextView et_name;
    TextView et_date;
    TextView et_time;
    TextView et_note;
    Calendar calendar;

    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (AddPlanDialog.Listener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement AddPlanDialog.Listener");
        }
    }

    @NonNull @Override public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_plan, null);
        et_name = view.findViewById(R.id.addplan_et_name);
        et_date = view.findViewById(R.id.addplan_et_date);
        et_time = view.findViewById(R.id.addplan_et_time);
        et_note = view.findViewById(R.id.addplan_et_note);
        calendar = Calendar.getInstance();

        et_date.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                et_date_onclick(view);
            }
        });
        et_time.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                et_time_onclick(view);
            }
        });

        builder.setView(view);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                listener.btn_submit_onclick(AddPlanDialog.this);
            }
        });

        return builder.create();
    }

    private void et_date_onclick(View view) {
        DatePickerDialog date_picker_dialog = new DatePickerDialog(
            this.getContext(),
            new DatePickerDialog.OnDateSetListener() {
                @Override public void onDateSet(DatePicker view, int year, int month, int day) {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, day);
                    et_date.setText(Helper.to_date_format(calendar.getTime()));
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        );
        date_picker_dialog.show();
    }

    private void et_time_onclick(View view) {
        TimePickerDialog time_picker_dialog = new TimePickerDialog(
            this.getContext(),
            new TimePickerDialog.OnTimeSetListener() {
                @Override public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);
                    et_time.setText(Helper.to_time_format(calendar.getTime()));
                }
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        );
        time_picker_dialog.show();
    }
}
