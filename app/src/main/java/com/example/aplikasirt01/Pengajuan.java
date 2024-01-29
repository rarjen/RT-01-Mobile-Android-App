package com.example.aplikasirt01;

public class Pengajuan {
    private String nama;
    private String nik;
    private String tglLahir;

    // Konstruktor kosong diperlukan untuk Firebase Realtime Database
    public Pengajuan() {
    }

    public Pengajuan(String nama, String nik, String tglLahir) {
        this.nama = nama;
        this.nik = nik;
        this.tglLahir = tglLahir;
    }

    // Buat getter dan setter sesuai kebutuhan
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

}
