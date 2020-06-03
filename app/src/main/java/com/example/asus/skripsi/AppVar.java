package com.example.asus.skripsi;

public class AppVar {
    //URL to our login.php file, url bisa diganti sesuai dengan alamat server kita
    public static final String LOGIN_URL = "http://192.168.100.54/exam/android_api/login.php";
    public static final String GETMAHASISWA_URL = "http://192.168.100.54/exam/android_api/getmahasiswa.php";
    public static final String GETDAFTARUJIAN_URL = "http://192.168.100.54/exam/android_api/daftar_ujian.php";
    public static final String TOKEN_URL = "http://192.168.100.54/exam/android_api/form_token.php";
    public static final String GETSOALUJIAN_URL = "http://192.168.100.54/exam/mahasiswa/ujian_api";
    public static final String INSERTDATA_URL = "http://192.168.100.54/exam/mahasiswa/insertdata";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_USERNAME= "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NIM= "nim";
    public static final String KEY_TOKEN= "token_ujian";
    public static final String KEY_IDUJIAN= "id_ujian";
    public static final String KEY_IDSOAL= "id_soal";

    public static final String KEY_LOGIN= "key_login";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";
    public static final String TOKEN_FAILED = "failure";
    public static final String TOKEN_SUCCESS = "success";

}
