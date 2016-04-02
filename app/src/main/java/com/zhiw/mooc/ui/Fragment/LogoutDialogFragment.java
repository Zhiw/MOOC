package com.zhiw.mooc.ui.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * ClassName: LogoutDialogFragment
 * Desc:
 * Created by zhiw on 16/3/31.
 */
public class LogoutDialogFragment extends DialogFragment {

    public interface LogoutDialogListener{
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    LogoutDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Log out ")
                .setMessage("Are you sure?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onDialogPositiveClick(LogoutDialogFragment.this);

                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onDialogNegativeClick(LogoutDialogFragment.this);

                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (LogoutDialogListener)activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}
