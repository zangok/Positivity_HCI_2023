package com.example.positivity_hci_2023;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.positivity_hci_2023.databinding.ActivityMainBinding;
import com.example.positivity_hci_2023.ui.resources.ResourcesFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_meetup, R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
        ).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Hide the action bar if it's not needed
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
            getSupportActionBar().setTitle("Positivity");
        }
    }

    @Override
    public void onBackPressed() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        // Use the ID from the navigation graph
        if (navController.getCurrentDestination().getId() == R.id.navigation_resources) {
            ResourcesFragment resourcesFragment = (ResourcesFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.navigation_resources); // Use the ID from the navigation graph
            if (resourcesFragment != null && resourcesFragment.canGoBack()) {
                // If canGoBack returns true, back action was handled by the fragment
                return;
            }
        }
        // If not handled by ResourcesFragment, call the super method to handle default back action
        super.onBackPressed();
    }
}
