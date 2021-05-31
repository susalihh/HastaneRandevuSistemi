package com.example.hastanerandevusistemi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RandevuActivity extends AppCompatActivity {

    Veritabani vt;
    Veritabani2 vt2;
    ArrayList<HashMap<String, String>> randevu_liste;
    String[] hasta;
    Button btnRandevuAl,btnRandevularim,btnBilgiler;
    CalendarView cv;
    Spinner spin1,spin2,spin3;
    ArrayAdapter adapter1,adapter2,adapter3,adapter4,adapter5,adapter6,adapter7,adapter8,adapter9,adapter10,adapter11,adapter12,adapter13;
    String tmpSaat,tmpBrans,tmpDoktor,tmpTarih;
    String[] saat = {"09:00","09:30","10:00","10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30"};
    String[] doktorbeslenme = {"Dr. Ali","Dr. Ahmet"};
    String[] doktorbeyin = {"Dr. Mehmet","Dr. Ayşe"};
    String[] doktorcocuk = {"Dr. Fatma","Dr. Mustafa","Dr. Hasan"};
    String[] doktorcildiye = {"Dr. Aslıhan"};
    String[] doktorfiziksel = {"Dr. Ayşenur"};
    String[] doktorgenelcerrahi = {"Dr. Burhan","Dr. Tahsin"};
    String[] doktorgöz = {"Dr. Aysun","Dr. Salih","Dr. Serhat"};
    String[] doktorkardiyoloji = {"Dr. Şeyma"};
    String[] doktorkulak = {"Dr. Büşra","Dr. Seda"};
    String[] doktorortopedi = {"Dr. Buğra","Dr. Halil","Dr. Derya"};
    String[] doktorpsikiyatri = {"Dr. Burak","Dr. Asiye"};
    String tmpTc,tmpAd,tmpSifre,tmpTel;
    String[] brans = {"BESLENME VE DİYET","BEYİN VE SİNİR CERRAHİSİ","ÇOCUK SAĞLIĞI VE HASTALIKLARI","DERMATOLOJİ (CİLDİYE)","FİZİKSEL TIP VE REHABİLİTASYON","GENEL CERRAHİ","GÖZ HASTALIKLARI","KARDİYOLOJİ","KULAK-BURUN-BOĞAZ HASTALIKLARI","ORTOPEDİ VE TRAVMATOLOJİ","PSİKİYATRİ"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randevu);

        adapter2 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,doktorbeslenme);
        adapter4 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,doktorbeyin);
        adapter5 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,doktorcocuk);
        adapter6 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,doktorcildiye);
        adapter7 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,doktorfiziksel);
        adapter8 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,doktorgenelcerrahi);
        adapter9 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,doktorgöz);
        adapter10 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,doktorkardiyoloji);
        adapter11 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,doktorkulak);
        adapter12 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,doktorortopedi);
        adapter13 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,doktorpsikiyatri);

        btnRandevuAl = findViewById(R.id.button6);
        btnRandevularim = findViewById(R.id.button7);
        btnBilgiler = findViewById(R.id.button8);
        cv = findViewById(R.id.calendarView);
        spin1 = findViewById(R.id.spinner);
        spin2 = findViewById(R.id.spinner2);
        spin3 = findViewById(R.id.spinner3);

        adapter3 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, saat);
        spin3.setAdapter(adapter3);
        spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tmpSaat = saat[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        adapter1 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,brans);
        spin1.setAdapter(adapter1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tmpBrans = brans[position];
                if (spin1.getSelectedItemPosition() == 0){
                    spin2.setAdapter(adapter2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tmpDoktor = doktorbeslenme[position];
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if (spin1.getSelectedItemPosition() == 1){
                    spin2.setAdapter(adapter4);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tmpDoktor = doktorbeyin[position];
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (spin1.getSelectedItemPosition() == 2){
                    spin2.setAdapter(adapter5);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tmpDoktor = doktorcocuk[position];
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (spin1.getSelectedItemPosition() == 3){
                    spin2.setAdapter(adapter6);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tmpDoktor = doktorcildiye[position];
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (spin1.getSelectedItemPosition() == 4){
                    spin2.setAdapter(adapter7);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tmpDoktor = doktorfiziksel[position];
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (spin1.getSelectedItemPosition() == 5){
                    spin2.setAdapter(adapter8);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tmpDoktor = doktorgenelcerrahi[position];
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (spin1.getSelectedItemPosition() == 6){
                    spin2.setAdapter(adapter9);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tmpDoktor = doktorgöz[position];
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (spin1.getSelectedItemPosition() == 7){
                    spin2.setAdapter(adapter10);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tmpDoktor = doktorkardiyoloji[position];
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (spin1.getSelectedItemPosition() == 8){
                    spin2.setAdapter(adapter11);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tmpDoktor = doktorkulak[position];
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (spin1.getSelectedItemPosition() == 9){
                    spin2.setAdapter(adapter12);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tmpDoktor = doktorortopedi[position];
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (spin1.getSelectedItemPosition() == 10){
                    spin2.setAdapter(adapter13);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            tmpDoktor = doktorpsikiyatri[position];
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int yil, int ay, int gun) {
                tmpTarih = gun + "/" + (ay+1) + "/" + yil;
            }
        });

        vt2 = new Veritabani2(this);
        try {
            vt2.baglantiyiAc();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        vt = new Veritabani(this);
        try {
            vt.baglantiyiAc();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String hastatc = getIntent().getExtras().getString("hastatc");
        btnRandevuAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vt2.randevuKayit(hastatc,tmpDoktor,tmpBrans,tmpTarih,tmpSaat);
                Toast.makeText(RandevuActivity.this, "Randevu alma işlemi başarılı", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(RandevuActivity.this);
        builder.setTitle("Randevularım");
        builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        btnBilgiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hasta = vt.hastaGetir(hastatc);
                tmpSifre = hasta[0];
                tmpAd = hasta[1];
                tmpTel = hasta[2];


                AlertDialog.Builder builder = new AlertDialog.Builder(RandevuActivity.this);
                builder.setCancelable(true);
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_dialog, null);
                builder.setView(customLayout);
                EditText t1 = customLayout.findViewById(R.id.et_text1);
                EditText t2 = customLayout.findViewById(R.id.et_text2);
                EditText t3 = customLayout.findViewById(R.id.et_text3);
                EditText t4 = customLayout.findViewById(R.id.et_text4);
                t1.setText(hastatc);
                t2.setText(tmpSifre);
                t3.setText(tmpAd);
                t4.setText(tmpTel);
                builder.setNegativeButton("GÜNCELLE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        vt.guncelleHasta(hastatc,t2.getText().toString(),t3.getText().toString(),t4.getText().toString());
                    }
                });
                builder.create().show();
            }
        });

        btnRandevularim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randevu_liste = vt2.randevuGetir(hastatc);
                String randevular = "";
                for(int i=0;i<randevu_liste.size();i++){
                    tmpDoktor = randevu_liste.get(i).get("doktor");
                    tmpBrans = randevu_liste.get(i).get("brans");
                    tmpTarih = randevu_liste.get(i).get("tarih");
                    tmpSaat = randevu_liste.get(i).get("saat");
                    randevular += (i+1)+") Doktor: "+tmpDoktor+"\nPolikinlik: "+tmpBrans+"\nTarih: "+tmpTarih+" "+tmpSaat+"\n";
                }
                builder.setMessage(randevular);
                builder.show();
            }
        });

    }
}