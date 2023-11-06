package com.example.positivity_hci_2023.ui.meetup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.positivity_hci_2023.databinding.FragmentMeetupBinding;
import com.example.positivity_hci_2023.ui.meetup.MeetupViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class MeetupFragment extends Fragment {

    private FragmentMeetupBinding binding;
    private TextInputEditText textInputEditText;
    private TextView textMeetup;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MeetupViewModel meetupViewModel =
                new ViewModelProvider(this).get(MeetupViewModel.class);

        binding = FragmentMeetupBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        textInputEditText = binding.textInputEditText;
        textMeetup = binding.textMeetup;

        final TextView textView = binding.textMeetup;
        meetupViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        Button confirmButton = binding.button;
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the TextInputEditText
                textInputEditText.setText("");

                // Change the TextView
                textMeetup.setText("Great job, you've logged an meetup!"); // Replace "New Text" with the desired text
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}