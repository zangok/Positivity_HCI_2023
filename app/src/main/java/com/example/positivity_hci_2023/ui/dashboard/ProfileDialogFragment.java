package com.example.positivity_hci_2023.ui.dashboard;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.example.positivity_hci_2023.R;

public class ProfileDialogFragment extends DialogFragment {

    public interface ProfileDialogListener {
        void onSendRequestClicked(String profileName);
    }

    private ProfileDialogListener listener;

    public static final String ARG_PROFILE_NAME = "profile_name";
    public static final String ARG_PROFILE_AGE = "profile_age";
    public static final String ARG_PROFILE_INFO = "profile_info";

    public static ProfileDialogFragment newInstance(String profileName, int profileAge, String profileInfo) {
        ProfileDialogFragment fragment = new ProfileDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PROFILE_NAME, profileName);
        args.putInt(ARG_PROFILE_AGE, profileAge);
        args.putString(ARG_PROFILE_INFO, profileInfo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ProfileDialogListener) {
            listener = (ProfileDialogListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement ProfileDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String profileName = getArguments().getString(ARG_PROFILE_NAME, "Unknown");
        int profileAge = getArguments().getInt(ARG_PROFILE_AGE, -1);
        String profileInfo = getArguments().getString(ARG_PROFILE_INFO, "");

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_profile, null);

        ImageView profileImageView = dialogView.findViewById(R.id.profile_image);
        TextView profileNameTextView = dialogView.findViewById(R.id.profile_name);
        TextView profileAgeTextView = dialogView.findViewById(R.id.profile_age);
        TextView profileInfoTextView = dialogView.findViewById(R.id.profile_info);

        profileImageView.setImageResource(R.drawable.profile_picture); // Ensure this drawable exists
        profileNameTextView.setText(profileName);
        profileAgeTextView.setText(getString(R.string.profile_age, profileAge));
        profileInfoTextView.setText(profileInfo);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView)
                .setPositiveButton("Send Request", (dialog, id) -> {
                    listener.onSendRequestClicked(profileName); // Notify the MainActivity
                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    // User cancelled the dialog
                });

        return builder.create();
    }
}
