package id.my.avmmartin.goldexperience;

import android.app.Dialog;
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
    private LayoutInflater inflater;
    private View view;
    private TextView et_name;
    private TextView et_date;
    private TextView et_time;
    private TextView et_note;
    private Place place;

    AddPlanDialog(Place _place) {
        inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.template_addplan, null);
        et_name = view.findViewById(R.id.t_addplan_et_name);
        et_date = view.findViewById(R.id.t_addplan_et_date);
        et_time = view.findViewById(R.id.t_addplan_et_time);
        et_note = view.findViewById(R.id.t_addplan_et_note);
        place = _place;
    }

    @NonNull @Override public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder
            .setView(view)
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            })
            .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int which) {
                    btn_submit_onclick(dialog, which);
                }
            });

        return builder.create();
    }

    private void btn_submit_onclick(DialogInterface dialog, int which) {
        // TODO QUIZ: submit data
    }
}
