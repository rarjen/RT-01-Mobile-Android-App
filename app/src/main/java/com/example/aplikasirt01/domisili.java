package com.example.aplikasirt01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aplikasirt01.R;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.aplikasirt01.Menu_utama;
import com.example.aplikasirt01.Pengguna;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class domisili extends AppCompatActivity {

    private static final String TAG = "Domisili"; // Tambahkan tag untuk log

    private EditText editTextEditNama, editTexEditNik, editTextEditTgl;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domisili);

        editTextEditNama = findViewById(R.id.edit_nama);
        editTexEditNik = findViewById(R.id.edit_nik);
        editTextEditTgl = findViewById(R.id.edit_tgl);

        Button buttonSimpan = findViewById(R.id.button_simpan);
        progressBar = findViewById(R.id.progressBar);

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textNama = editTextEditNama.getText().toString();
                String textNik = editTexEditNik.getText().toString();
                String textTgl = editTextEditTgl.getText().toString();

                if (TextUtils.isEmpty(textNama)) {
                    showToast("Masukkan nama lengkap", editTextEditNama);
                } else if (TextUtils.isEmpty(textNik)) {
                    showToast("Masukkan NIK sesuai KTP", editTexEditNik);
                } else if (textNik.length() != 16) {
                    showToast("NIK harus terdiri dari 16 angka", editTexEditNik);
                } else if (TextUtils.isEmpty(textTgl)) {
                    showToast("Masukkan keterangan", editTextEditTgl);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    registerUser(textNama, textNik, textTgl);
                }
            }
        });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("pengajuan");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Pengajuan pengajuan = dataSnapshot.getValue(Pengajuan.class);
                    if (pengajuan != null) {
                        Log.d(TAG, "Data pengajuan: " + pengajuan.getNama());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void registerUser(String textNama, String textNik, String textTgl) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("pengajuan");

        Pengajuan pengajuan = new Pengajuan(textNama, textNik, textTgl);

        reference.push().setValue(pengajuan)
                .addOnSuccessListener(aVoid -> {
                    progressBar.setVisibility(View.GONE);
                    showToast("Data berhasil disimpan", null);
                    Intent intent = new Intent(domisili.this, Menu_utama.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    progressBar.setVisibility(View.GONE);
                    showToast("Terjadi kesalahan. Silakan coba lagi.", null);
                });
    }

    private void showToast(String message, View view) {
        Toast.makeText(domisili.this, message, Toast.LENGTH_LONG).show();
        if (view instanceof EditText) {
            ((EditText) view).setError("Field ini harus diisi");
            view.requestFocus();
        }
    }
}
