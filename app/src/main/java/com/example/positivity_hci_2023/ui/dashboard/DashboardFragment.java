package com.example.positivity_hci_2023.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import android.widget.TextView;
import com.example.positivity_hci_2023.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private static final String ANASTASIA_NAME = "Anastasia Beaverhousen";
    private static final int ANASTASIA_AGE = 17;
    private static final String ANASTASIA_INFO = "Likes nature and music, open to meeting new positivity pals.";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Assuming all marker ImageViews have been added to the layout
        ImageView[] palMarkers = new ImageView[]{
                binding.palMarker1,
                binding.palMarker2,
                binding.palMarker3,
                binding.palMarker4
        };

        for (ImageView marker : palMarkers) {
            marker.setOnClickListener(v -> showProfileDialog(ANASTASIA_NAME, ANASTASIA_AGE, ANASTASIA_INFO));
        }

        return root;
    }

    private void showProfileDialog(String profileName, int profileAge, String profileInfo) {
        DialogFragment dialogFragment = ProfileDialogFragment.newInstance(profileName, profileAge, profileInfo);
        dialogFragment.show(getParentFragmentManager(), "profileDialog");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}