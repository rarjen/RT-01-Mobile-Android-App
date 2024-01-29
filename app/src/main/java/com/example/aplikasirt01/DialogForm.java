package com.example.aplikasirt01;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogForm extends DialogFragment {

    String nama_lengkap,  nik_ktp,  tempat_tgl_lahir_dd_mm_yyyy,  jenis_kelamin_l_p,  pekerjaan, agama, status_lajang_kawin, no_rumah_ex_blok_a10;
    DatabaseReference databases = FirebaseDatabase.getInstance().getReference();

    public DialogForm() {
        this.nama_lengkap = nama_lengkap;
        this.nik_ktp = nik_ktp;
        this.tempat_tgl_lahir_dd_mm_yyyy = tempat_tgl_lahir_dd_mm_yyyy;
        this.jenis_kelamin_l_p = jenis_kelamin_l_p;
        this.pekerjaan = pekerjaan;
        this.agama = agama;
        this.status_lajang_kawin = status_lajang_kawin;
        this.no_rumah_ex_blok_a10 = no_rumah_ex_blok_a10;
    }
    TextView et_nama;
    TextView et_nik;
    TextView et_tgl;
    TextView et_jenis;
    TextView et_pekerjaan;
    TextView et_agama;
    TextView et_status;
    TextView et_alamat;

    Button btn_simpan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.input_form,container, false);

        et_nama = view.findViewById(R.id.et_nama);
        et_nik = view.findViewById(R.id.et_nik);
        et_tgl = view.findViewById(R.id.et_tgl);
        et_jenis = view.findViewById(R.id.et_jenis);
        et_agama = view.findViewById(R.id.et_agama);
        et_pekerjaan = view.findViewById(R.id.et_pekerjaan);
        et_status = view.findViewById(R.id.et_status);
        et_alamat = view.findViewById(R.id.et_alamat);

        et_nama.setText(nama_lengkap);
        et_nik.setText(nik_ktp);
        et_tgl.setText(tempat_tgl_lahir_dd_mm_yyyy);
        et_jenis.setText(jenis_kelamin_l_p);
        et_agama.setText(agama);
        et_pekerjaan.setText(pekerjaan);
        et_status.setText(status_lajang_kawin);
        et_alamat.setText(no_rumah_ex_blok_a10);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama_lengkap = et_nama.getText().toString();
                String nik_ktp = et_nik.getText().toString();
                String tempat_tgl_lahir_dd_mm_yyyy = et_tgl.getText().toString();
                String jenis_kelamin_l_p = et_jenis.getText().toString();
                String pekerjaan = et_pekerjaan.getText().toString();
                String agama = et_agama.getText().toString();
                String status_lajang_kawin = et_status.getText().toString();
                String no_rumah_ex_blok_a10= et_alamat.getText().toString();

                if(TextUtils.isEmpty(nama_lengkap)) {
                input((EditText) et_nama,"Nama");
                } else if (TextUtils.isEmpty(nik_ktp)) {
                    input((EditText) et_nik, "NIK");
                } else if (TextUtils.isEmpty(tempat_tgl_lahir_dd_mm_yyyy)) {
                    input((EditText) et_tgl, "Tempat/Tgl lahir");
                } else if (TextUtils.isEmpty(jenis_kelamin_l_p)) {
                    input((EditText) et_jenis, "Jenis Kelamin");
                } else if (TextUtils.isEmpty(pekerjaan)) {
                    input((EditText) et_pekerjaan, "Pekerjaan");
                } else if (TextUtils.isEmpty(agama)) {
                    input((EditText) et_agama, "Agama");
                } else if (TextUtils.isEmpty(status_lajang_kawin)) {
                    input((EditText) et_status, "Status");
                } else if (TextUtils.isEmpty(no_rumah_ex_blok_a10)) {
                    input((EditText) et_alamat, "No Rumah");

            } else{
                    databases.child("Data").push().setValue(new Pengguna(nama_lengkap, nik_ktp,tempat_tgl_lahir_dd_mm_yyyy,jenis_kelamin_l_p,pekerjaan,agama,status_lajang_kawin,no_rumah_ex_blok_a10)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(view.getContext(), "Data tersimpan", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(view.getContext(), "Data gagal tersimpan", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

        return view;

    }

    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        }

}
    private void input(EditText txt, String s) {
        txt.setError(s + " tidak boleh kosong");
        txt.requestFocus();
    }

}

