package com.example.positivity_hci_2023.ui.meetup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.positivity_hci_2023.databinding.FragmentMeetupBinding;
import com.example.positivity_hci_2023.ui.meetup.MeetupViewModel;

public class MeetupFragment extends Fragment {

    private FragmentMeetupBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MeetupViewModel meetupViewModel =
                new ViewModelProvider(this).get(MeetupViewModel.class);

        binding = FragmentMeetupBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMeetup;
        meetupViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}