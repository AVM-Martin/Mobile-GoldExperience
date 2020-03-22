package id.my.avmmartin.goldexperience;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

final class AddPlanDialog extends DialogFragment {
    public interface AddPlanDialogListener {
        void addplandialog_btn_submit_onclick(AddPlanDialog dialog);
    }

    private AddPlanDialogListener listener;
    TextView et_name;
    TextView et_date;
    TextView et_time;
    TextView et_note;

    @Override public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (AddPlanDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement AddPlanDialogListener");
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

        builder
            .setView(view)
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            })
            .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int which) {
                    listener.addplandialog_btn_submit_onclick(AddPlanDialog.this);
                }
            });

        return builder.create();
    }
}
