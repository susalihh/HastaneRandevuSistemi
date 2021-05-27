package com.example.hastanerandevusistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Veritabani vt;
    Button btnGiris,btnUyeOl,btnYoneticiGiris,btnCikis;
    EditText tc,sifre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tc = findViewById(R.id.tcText);
        sifre = findViewById(R.id.passwordText);

        vt = new Veritabani(this);
        try {
            vt.baglantiyiAc();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        btnGiris = findViewById(R.id.button);
        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hastatc = tc.getText().toString();
                String hastapassword = sifre.getText().toString();
                String sifre = vt.girisHasta(hastatc);
                if(hastapassword.equals(sifre))
                {
                    Toast.makeText(MainActivity.this, "Giriş başarılı", Toast.LENGTH_LONG).show();
                    Intent intentlogin = new Intent(getApplicationContext(),RandevuActivity.class);
                    intentlogin.putExtra("hastatc", hastatc);
                    startActivity(intentlogin);
                } else
                {
                    Toast.makeText(MainActivity.this, "Hasta T.C. No veya şifresi yanlış!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnUyeOl = findViewById(R.id.button2);
        btnUyeOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, UyeOlActivity.class);
                startActivity(intent);
            }
        });

        btnYoneticiGiris = findViewById(R.id.button3);
        btnYoneticiGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tc.getText().toString().equals("1") && sifre.getText().toString().equals("1")){
                    Intent intent=new Intent(MainActivity.this, YoneticiActivity.class);
                    startActivity(intent);
                }else
                    Toast.makeText(MainActivity.this, "Yönetici T.C. No veya şifresi yanlış!", Toast.LENGTH_LONG).show();
            }
        });

        btnCikis = findViewById(R.id.button4);
        btnCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}