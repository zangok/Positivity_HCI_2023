package com.example.positivity_hci_2023.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.positivity_hci_2023.Notifications;
import com.example.positivity_hci_2023.R;
import com.example.positivity_hci_2023.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button buttonTimeNotifications = root.findViewById(R.id.buttonTimeNotifications);
        buttonTimeNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call your method when the button is clicked
                Notifications notifications = new Notifications(root.getContext());
            }
        });
        Button btnGoToNotificationSettings = binding.btnGoToNotificationSettings;
        btnGoToNotificationSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open Android notification settings
                Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, requireContext().getPackageName());
                startActivity(intent);
            }
        });

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}