package com.example.hastanerandevusistemi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.SQLException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

public class YoneticiActivity extends AppCompatActivity {

    Veritabani vt1;
    Veritabani2 vt2;
    ListView lv;
    Spinner spin1;
    EditText t1;
    String tmpTc,tmpSifre,tmpAd,tmpTel,tmpId,tmphastatc,tmpdoktor,tmpbrans,tmptarih,tmpsaat;
    boolean tmp = true;
    ArrayAdapter adapter1;
    String[] secimListe = {"Hastaları Listele","Randevuları Listele"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yonetici);

        t1 = findViewById(R.id.tcText3);
        spin1 = findViewById(R.id.spinner4);
        lv = findViewById(R.id.listView1);

        vt1 = new Veritabani(this);
        try {
            vt1.baglantiyiAc();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        vt2 = new Veritabani2(this);
        try {
            vt2.baglantiyiAc();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        adapter1 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,secimListe);
        spin1.setAdapter(adapter1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spin1.getSelectedItemPosition() == 0){
                    tmp = true;
                    hastaListele();
                    ListViewItem();
                }
                else{
                    tmp = false;
                    randevuListele();
                    ListViewItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        t1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (tmp == true){
                        String hastatc = t1.getText().toString();
                        List<String> list = vt1.hastaAra(hastatc);
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(YoneticiActivity.this, android.R.layout.simple_list_item_1,android.R.id.text1,list);
                        lv.setAdapter(adapter);
                        if (t1.length() == 0)
                            hastaListele();
                    }
                    else if(tmp == false){
                        String hastatc = t1.getText().toString();
                        List<String> list = vt2.randevuAra(hastatc);
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(YoneticiActivity.this, android.R.layout.simple_list_item_1,android.R.id.text1,list);
                        lv.setAdapter(adapter);
                        if (t1.length() == 0)
                            randevuListele();
                    }


                }catch(Exception ex){

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void ListViewItem(){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (tmp == true) {
                    String item = lv.getItemAtPosition(position).toString();
                    String[] itemBol = item.split(" - ");
                    tmpTc = itemBol[0].toString();
                    tmpSifre = itemBol[1].toString();
                    tmpAd = itemBol[2].toString();
                    tmpTel = itemBol[3].toString();

                    AlertDialog.Builder builder = new AlertDialog.Builder(YoneticiActivity.this);
                    builder.setCancelable(true);
                    final View customLayout = getLayoutInflater().inflate(R.layout.custom_dialog, null);
                    builder.setView(customLayout);
                    EditText t1 = customLayout.findViewById(R.id.et_text1);
                    EditText t2 = customLayout.findViewById(R.id.et_text2);
                    EditText t3 = customLayout.findViewById(R.id.et_text3);
                    EditText t4 = customLayout.findViewById(R.id.et_text4);
                    t1.setText(tmpTc);
                    t2.setText(tmpSifre);
                    t3.setText(tmpAd);
                    t4.setText(tmpTel);
                    builder.setNegativeButton("GÜNCELLE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            vt1.guncelleHasta(tmpTc,t2.getText().toString(),t3.getText().toString(),t4.getText().toString());
                            hastaListele();
                        }
                    });
                    builder.setPositiveButton("SİL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            vt1.silHastaKayit(tmpTc);
                            hastaListele();
                        }
                    });
                    builder.create().show();
                }else if(tmp == false){
                    String item = lv.getItemAtPosition(position).toString();
                    String[] itemBol = item.split(" - ");
                    tmpId = itemBol[0].toString();
                    tmphastatc = itemBol[1].toString();
                    tmpdoktor = itemBol[2].toString();
                    tmpbrans = itemBol[3].toString();
                    tmptarih = itemBol[4].toString();
                    tmpsaat = itemBol[5].toString();

                    AlertDialog.Builder builder = new AlertDialog.Builder(YoneticiActivity.this);
                    builder.setCancelable(true);
                    final View customLayout = getLayoutInflater().inflate(R.layout.custom_dialog2, null);
                    builder.setView(customLayout);
                    EditText t1 = customLayout.findViewById(R.id.et_text1);
                    EditText t2 = customLayout.findViewById(R.id.et_text2);
                    EditText t3 = customLayout.findViewById(R.id.et_text3);
                    EditText t4 = customLayout.findViewById(R.id.et_text4);
                    EditText t5 = customLayout.findViewById(R.id.et_text5);
                    EditText t6 = customLayout.findViewById(R.id.et_text6);
                    t1.setText(tmpId);
                    t2.setText(tmphastatc);
                    t3.setText(tmpdoktor);
                    t4.setText(tmpbrans);
                    t5.setText(tmptarih);
                    t6.setText(tmpsaat);
                    builder.setNegativeButton("GÜNCELLE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            vt2.guncelleRandevu(tmpId,t2.getText().toString(),t3.getText().toString(),t4.getText().toString(),t5.getText().toString(),t6.getText().toString());
                            randevuListele();
                        }
                    });
                    builder.setPositiveButton("SİL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            vt2.silRandevuKayit(tmpId);
                            randevuListele();
                        }
                    });
                    builder.create().show();
                }
            }
        });
    }

    public void hastaListele(){
        List<String> list = vt1.hastaListele();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(YoneticiActivity.this, android.R.layout.simple_list_item_1,android.R.id.text1,list);
        lv.setAdapter(adapter);
    }
    public void randevuListele(){
        List<String> list = vt2.randevuListeGetir();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(YoneticiActivity.this, android.R.layout.simple_list_item_1,android.R.id.text1,list);
        lv.setAdapter(adapter);
    }
}