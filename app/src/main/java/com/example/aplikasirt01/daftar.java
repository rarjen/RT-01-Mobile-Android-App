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

public class daftar extends AppCompatActivity {

    private static final String TAG = "Daftar";

    private EditText editTextEditNama, editTexEditNik, editTextEditTgl, editTextEditPekerjaan, editTextEditAgama,
            editTextEditAlamat;
    private ProgressBar progressBar;
    private RadioGroup radioGroupJenis, radioGroupStatus;
    private RadioButton radioButtonJenisSelected, radioButtonStatusSelested;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        editTextEditNama = findViewById(R.id.editNama);
        editTexEditNik = findViewById(R.id.editNik);
        editTextEditTgl = findViewById(R.id.editTgl);
        editTextEditPekerjaan = findViewById(R.id.editPekerjaan);
        editTextEditAgama = findViewById(R.id.editAgama);
        editTextEditAlamat = findViewById(R.id.editAlamat);
        progressBar = findViewById(R.id.progressBar);

        radioGroupJenis = findViewById(R.id.editJenis);
        radioGroupStatus = findViewById(R.id.editStatus);

        findViewById(R.id.button_simpan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAndRegisterUser();
            }
        });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Pengguna");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Pengguna pengguna = dataSnapshot.getValue(Pengguna.class);
                    if (pengguna != null) {
                        Log.d(TAG, "Data pengguna: " + pengguna.getNama_lengkap());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void validateAndRegisterUser() {
        // Code validasi dan registrasi user
        // ...

        // Misalnya:
        String textNama = editTextEditNama.getText().toString();
        String textNik = editTexEditNik.getText().toString();
        String textTgl = editTextEditTgl.getText().toString();
        String textPekerjaan = editTextEditPekerjaan.getText().toString();
        String textAgama = editTextEditAgama.getText().toString();
        String textAlamat = editTextEditAlamat.getText().toString();
        String textJenis;
        String textStatus;

        // Validasi dan registrasi user
        // ...

        if (TextUtils.isEmpty(textNama)) {
            showToast("Masukkan nama lengkap", editTextEditNama);
        } else if (TextUtils.isEmpty(textNik)) {
            showToast("Masukkan NIK sesuai KTP", editTexEditNik);
        } else if (textNik.length() != 16) {
            showToast("NIK harus terdiri dari 16 angka", editTexEditNik);
        } else if (TextUtils.isEmpty(textTgl)) {
            showToast("Masukkan Tempat dan Tgl Lahir sesuai KTP", editTextEditTgl);
        } else if (TextUtils.isEmpty(textPekerjaan)) {
            showToast("Masukkan pekerjaan anda saat ini", editTextEditPekerjaan);
        } else if (TextUtils.isEmpty(textAgama)) {
            showToast("Masukkan agama anda saat ini", editTextEditAgama);
        } else if (TextUtils.isEmpty(textAlamat)) {
            showToast("Masukkan no rumah saat ini", editTextEditAlamat);
        } else if (radioGroupJenis.getCheckedRadioButtonId() == -1) {
            showToast("Pilih Jenis Kelamin", radioButtonJenisSelected);
        } else if (radioGroupStatus.getCheckedRadioButtonId() == -1) {
            showToast("Pilih status anda saat ini", radioButtonStatusSelested);
        } else {
            textJenis = radioButtonJenisSelected.getText().toString();
            textStatus = radioButtonStatusSelested.getText().toString();
            progressBar.setVisibility(View.VISIBLE);
            registerUser(textNama, textNik, textTgl, textJenis, textPekerjaan, textAgama, textAlamat, textStatus);
        }
    }

    private void registerUser(String textNama, String textNik, String textTgl, String textJenis, String textPekerjaan, String textAgama, String textAlamat, String textStatus) {
        // Code registrasi user ke Firebase Database
        // ...

        // Misalnya:
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Pengguna");

        Pengguna pengguna = new Pengguna(textNama, textNik, textTgl, textJenis, textPekerjaan, textAgama, textAlamat, textStatus);

        reference.push().setValue(pengguna)
                .addOnSuccessListener(aVoid -> {
                    progressBar.setVisibility(View.GONE);
                    showToast("Data berhasil disimpan", null);
                    Intent intent = new Intent(this, Menu_utama.class);
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
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        if (view instanceof EditText) {
            ((EditText) view).setError("Field ini harus diisi");
            view.requestFocus();
        } else if (view instanceof RadioButton) {
            ((RadioButton) view).setError("Pilih salah satu");
            view.requestFocus();
        }
    }
}
