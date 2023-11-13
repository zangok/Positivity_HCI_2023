package com.example.positivity_hci_2023;

import android.os.Bundle;
import android.view.View;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.bottomnavigation.BottomNavigationView; // Add this import
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.positivity_hci_2023.databinding.ActivityMainBinding;
import com.example.positivity_hci_2023.ui.dashboard.ProfileDialogFragment;

public class MainActivity extends AppCompatActivity implements ProfileDialogFragment.ProfileDialogListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Now that BottomNavigationView is imported, this should work
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_meetup, R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
        ).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    @Override
    public void onBackPressed() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        if (navController.getCurrentDestination() != null && navController.getCurrentDestination().getId() == R.id.navigation_resources) {
            // Assuming ResourcesFragment handles its own back navigation
            // If canGoBack returns true, back action was handled by the fragment
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public void onSendRequestClicked(String profileName) {
        // Find the root view of the layout
        View rootView = findViewById(android.R.id.content);
        Snackbar.make(rootView, "Request sent to " + profileName, Snackbar.LENGTH_LONG).show();
    }
}