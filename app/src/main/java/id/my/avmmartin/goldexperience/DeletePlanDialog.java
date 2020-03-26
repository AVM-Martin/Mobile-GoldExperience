package id.my.avmmartin.goldexperience;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

class DeletePlanDialog extends DialogFragment {
    public interface DeletePlanDialogListener {
        void deleteplandialog_btn_submit_onclick(DeletePlanDialog dialog);
    }

    private DeletePlanDialog.DeletePlanDialogListener listener;
    Plan plan;

    void set_datas(Plan _plan) {
        plan = _plan;
    }

    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (DeletePlanDialog.DeletePlanDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement DeletePlanDialogListener");
        }
    }

    @NonNull @Override public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder
            .setMessage(getString(R.string.delete) + " " + plan.name + "?")
            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            })
            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int which) {
                    listener.deleteplandialog_btn_submit_onclick(DeletePlanDialog.this);
                }
            });

        return builder.create();
    }
}
