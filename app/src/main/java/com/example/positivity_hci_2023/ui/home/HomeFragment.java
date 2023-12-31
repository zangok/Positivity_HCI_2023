package com.example.positivity_hci_2023.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.positivity_hci_2023.Notifications;
import com.example.positivity_hci_2023.PastMeetupViewModel;
import com.example.positivity_hci_2023.R;
import com.example.positivity_hci_2023.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.meetupText;

        PastMeetupViewModel model = new ViewModelProvider(requireActivity()).get(PastMeetupViewModel.class);
        model.getUiState().observe(getViewLifecycleOwner(), uiState -> {
            // update UI
            Log.d("home",uiState);
            textView.setText(uiState);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}