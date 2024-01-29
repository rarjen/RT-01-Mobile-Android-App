package com.example.aplikasirt01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.GridLayout;

public class Menu_utama extends AppCompatActivity {

    DrawerLayout drawerLayout;
    GridLayout gridLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        drawerLayout = findViewById(R.id.drawer_layer);
        gridLayout = findViewById(R.id.gridLayout);

        // Set item click listener for GridLayout
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            final int position = i;
            View gridItem = gridLayout.getChildAt(i);
            gridItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleGridItemClick(position);
                }
            });
        }
    }

    private void handleGridItemClick(int position) {
        switch (position) {
            case 0:
                // Handle click on the first item in the grid (Daftar)
                // Example: Open a new activity
                startActivity(new Intent(Menu_utama.this, daftar.class));
                break;
            case 1:
                // Handle click on the second item in the grid (Domisili)
                // Example: Open a new activity
                startActivity(new Intent(Menu_utama.this, domisili.class));
                break;
            // Add more cases for other grid items as needed
        }
    }

    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }

    private void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void dashboard(View view) {
        Intent intent = new Intent(Menu_utama.this, Menu_utama.class);
        startActivity(intent);
    }

    public void info(View view) {
        Intent intent = new Intent(Menu_utama.this, info.class);
        startActivity(intent);
    }

    public void logout(View view) {
        logoutMenu(Menu_utama.this);
    }

    private void logoutMenu(Menu_utama menu_utama) {
        AlertDialog.Builder builder = new AlertDialog.Builder(menu_utama);
        builder.setTitle("Logout");
        builder.setMessage("Apakah anda yakin ingin keluar ?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}
