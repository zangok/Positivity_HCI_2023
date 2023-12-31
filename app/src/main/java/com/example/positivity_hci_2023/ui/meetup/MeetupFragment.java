package com.example.positivity_hci_2023.ui.meetup;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.positivity_hci_2023.PastMeetupViewModel;
import com.example.positivity_hci_2023.databinding.FragmentMeetupBinding;
import com.example.positivity_hci_2023.ui.home.HomeViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class MeetupFragment extends Fragment {

    private FragmentMeetupBinding binding;
    private TextInputEditText textInputEditText;
    private HomeViewModel homeViewModel;
    private TextView textMeetup;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MeetupViewModel meetupViewModel =
                new ViewModelProvider(this).get(MeetupViewModel.class);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentMeetupBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        textInputEditText = binding.textInputEditText;
        textMeetup = binding.textMeetup;

        meetupViewModel.getText().observe(getViewLifecycleOwner(), textMeetup::setText);

        Button confirmButton = binding.button;
        confirmButton.setOnClickListener(v -> {
            //Updates text on home
            PastMeetupViewModel viewModel = new ViewModelProvider(requireActivity()).get(PastMeetupViewModel.class);
            viewModel.setUiState(textInputEditText.getText().toString());
            // Clear the TextInputEditText
            textInputEditText.setText("");

            // Change the TextView message
            textMeetup.setText("Great job, you've logged a meetup!");

            // Hide the keyboard
            hideKeyboardFrom(textInputEditText);
        });

        return root;
    }
    private void updateTextInHomeFragment(String newText) {
        // Update the LiveData in HomeViewModel with the new text
        homeViewModel.setText(newText);
    }
    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
