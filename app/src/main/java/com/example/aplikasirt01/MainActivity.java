package com.example.aplikasirt01;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pindahkan inisialisasi tombol ke dalam metode onCreate
        Button masukButton = findViewById(R.id.masuk);
        masukButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                masuk(view);
            }
        });
    }

    // Perbaikan: Metode masuk harus diletakkan dalam kelas
    public void masuk(View view) {
        Intent intent = new Intent(this, Menu_utama.class);
        startActivity(intent);
    }
}
