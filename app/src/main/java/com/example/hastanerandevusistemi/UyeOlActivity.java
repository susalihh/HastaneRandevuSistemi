package com.example.hastanerandevusistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UyeOlActivity extends AppCompatActivity {

    Veritabani vt;
    EditText tc,sifre,adsoyad,telefon;
    Button btnUyeOl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_ol);
        tc = findViewById(R.id.tcText2);
        sifre = findViewById(R.id.passwordText2);
        adsoyad = findViewById(R.id.adsoyadText);
        telefon = findViewById(R.id.telefonText);
        btnUyeOl = findViewById(R.id.button5);

        vt = new Veritabani(this);
        try {
            vt.baglantiyiAc();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        btnUyeOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vt.yeniHastaKayit(tc.getText().toString(),sifre.getText().toString(),adsoyad.getText().toString(),telefon.getText().toString());
                Toast.makeText(UyeOlActivity.this, "Kayıt başarılı", Toast.LENGTH_SHORT).show();
            }
        });
    }
}