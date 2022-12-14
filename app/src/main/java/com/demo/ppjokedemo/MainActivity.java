package com.demo.ppjokedemo;

import android.os.Bundle;
import android.text.TextUtils;

import com.demo.ppjokedemo.util.NavGraphBuilder;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.demo.ppjokedemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
//        NavGraphBuilder.build(navController);
        NavGraphBuilder.build(navController,this,this.getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main).getId());
        navView.setOnItemSelectedListener(item -> {
            // do stuff
            navController.navigate(item.getItemId());
            return !TextUtils.isEmpty(item.getTitle());
        });
    }

}