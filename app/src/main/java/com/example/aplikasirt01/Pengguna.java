package com.example.aplikasirt01;

public class Pengguna {

    private String key;

    private String nama_lengkap;
    private String nik_ktp;
    private String tempat_tgl_lahir_dd_mm_yyyy;
    private String jenis_kelamin_l_p;
    private String pekerjaan;
    private String agama;
    private String status_lajang_kawin;
    private String no_rumah_ex_blok_a10;

    public Pengguna() {

    }

    public Pengguna(String nama_lengkap, String nik_ktp, String tempat_tgl_lahir_dd_mm_yyyy, String jenis_kelamin_l_p, String pekerjaan, String agama, String status_lajang_kawin, String no_rumah_ex_blok_a10) {
        this.nama_lengkap = nama_lengkap;
        this.nik_ktp = nik_ktp;
        this.tempat_tgl_lahir_dd_mm_yyyy = tempat_tgl_lahir_dd_mm_yyyy;
        this.jenis_kelamin_l_p = jenis_kelamin_l_p;
        this.pekerjaan = pekerjaan;
        this.agama = agama;
        this.status_lajang_kawin = status_lajang_kawin;
        this.no_rumah_ex_blok_a10 = no_rumah_ex_blok_a10;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getNik_ktp() {
        return nik_ktp;
    }

    public void setNik_ktp(String nik_ktp) {
        this.nik_ktp = nik_ktp;
    }

    public String getTempat_tgl_lahir_dd_mm_yyyy() {
        return tempat_tgl_lahir_dd_mm_yyyy;
    }

    public void setTempat_tgl_lahir_dd_mm_yyyy(String tempat_tgl_lahir_dd_mm_yyyy) {
        this.tempat_tgl_lahir_dd_mm_yyyy = tempat_tgl_lahir_dd_mm_yyyy;
    }

    public String getJenis_kelamin_l_p() {
        return jenis_kelamin_l_p;
    }

    public void setJenis_kelamin_l_p(String jenis_kelamin_l_p) {
        this.jenis_kelamin_l_p = jenis_kelamin_l_p;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getStatus_lajang_kawin() {
        return status_lajang_kawin;
    }

    public void setStatus_lajang_kawin(String status_lajang_kawin) {
        this.status_lajang_kawin = status_lajang_kawin;
    }

    public String getNo_rumah_ex_blok_a10() {
        return no_rumah_ex_blok_a10;
    }

    public void setNo_rumah_ex_blok_a10(String no_rumah_ex_blok_a10) {
        this.no_rumah_ex_blok_a10 = no_rumah_ex_blok_a10;
    }
}

