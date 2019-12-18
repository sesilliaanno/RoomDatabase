package com.example.roomdatabase.activity;

package com.esri.arcgisruntime.samples.deletefeaturesfeatureservice;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;

public class ConfirmDeleteFeatureDialog extends DialogFragment {

    private static final String ARG_FEATURE_ID = ConfirmDeleteFeatureDialog.class.getSimpleName() + "_feature_id";

    private String featureId;

    private DialogInterface.OnClickListener mOnClickListener = (dialog, which) -> {
        if (getContext() instanceof OnButtonClickedListener) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                ((OnButtonClickedListener) getContext()).onDeleteFeatureClicked(featureId);
            } else {
                dismiss();
            }
        }
    };

    public static ConfirmDeleteFeatureDialog newInstance(String featureId) {
        ConfirmDeleteFeatureDialog fragment = new ConfirmDeleteFeatureDialog();
        Bundle args = new Bundle();
        args.putString(ARG_FEATURE_ID, featureId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.featureId = getArguments().getString(ARG_FEATURE_ID);
    }

    @NonNull @Override public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setMessage(getString(R.string.dialog_confirm_delete_message, featureId))
                .setPositiveButton(R.string.dialog_confirm_delete_positive, mOnClickListener)
                .setNegativeButton(R.string.dialog_confirm_delete_negative, mOnClickListener)
                .create();
    }

    public interface OnButtonClickedListener {
        void onDeleteFeatureClicked(String featureId);
    }

}