package com.example.hastanerandevusistemi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Veritabani {
    private static final String DATABASE_ISIM = "randevu";
    private static final int DATABASE_VERSION = 1;
    private final Context con;
    private VeritabaniHelper veritabanihelper;
    private SQLiteDatabase veritabanim;

    //Hasta Tablosu
    private static final String DATABASE_TABLO = "hastalar";
    public static final String HASTA_TC = "tc";
    public static final String HASTA_SIFRE = "sifre";
    public static final String HASTA_ADSOYAD = "adsoyad";
    public static final String HASTA_TELEFON = "telefon";

    public Veritabani(Context c) {
        this.con = c;
    }
    public Veritabani baglantiyiAc() throws SQLException {
        veritabanihelper = new VeritabaniHelper(con);
        veritabanim = veritabanihelper.getWritableDatabase();
        return this;
    }
    public void baglantiyiKapat() {
        veritabanihelper.close();
    }
    public void yeniHastaKayit(String tc,String sifre,String adsoyad,String telefon){
        ContentValues val = new ContentValues();
        val.put("tc",tc);
        val.put("sifre",sifre);
        val.put("adsoyad",adsoyad);
        val.put("telefon",telefon);
        veritabanim.insert("hastalar",null,val);
    }
    public String girisHasta(String tc)
    {
        Cursor cursor = veritabanim.query("hastalar", null, " tc=?", new String[]{tc}, null, null, null);
        if(cursor.getCount()<1)
        {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        String sifre = cursor.getString(cursor.getColumnIndex("sifre"));
        cursor.close();
        return sifre;
    }

    public List<String> hastaListele(){
        List<String> veriler = new ArrayList<String>();
        veritabanim  = veritabanihelper.getReadableDatabase();
        try {
            String[] sutunlar = {HASTA_TC,HASTA_SIFRE,HASTA_ADSOYAD,HASTA_TELEFON};
            Cursor cursor = veritabanim.query(DATABASE_TABLO, sutunlar,null,null,null,null,null);
            while (cursor.moveToNext()){
                veriler.add(cursor.getInt(0)
                        + " - "
                        + cursor.getString(1)
                        + " - "
                        + cursor.getString(2)
                        + " - "
                        + cursor.getString(3));
            }
        }catch (Exception e){
        }
        return veriler;
    }

    public void silHastaKayit (String tc) {
        veritabanim.delete("hastalar","tc=?",new String[]{tc});
    }
    public void guncelleHasta(String guncellenecekTc, String sifreGuncelle, String adsoyadGuncelle, String telefonGuncelle) {
        ContentValues cvGuncelle = new ContentValues();
        cvGuncelle.put(HASTA_SIFRE, sifreGuncelle);
        cvGuncelle.put(HASTA_ADSOYAD, adsoyadGuncelle);
        cvGuncelle.put(HASTA_TELEFON, telefonGuncelle);
        veritabanim.update("hastalar",cvGuncelle,"tc=?",new String[]{guncellenecekTc});
    }

    public ArrayList<HashMap<String, String>> hastalar(){
        veritabanim = veritabanihelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DATABASE_TABLO;
        Cursor cursor = veritabanim.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> randevulist = new ArrayList<HashMap<String, String>>();
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0; i<cursor.getColumnCount();i++)
                {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }
                randevulist.add(map);
            } while (cursor.moveToNext());
        }
        veritabanim.close();
        return randevulist;
    }


    private static class VeritabaniHelper extends SQLiteOpenHelper {
        public VeritabaniHelper(Context contextim) {
            super(contextim, DATABASE_ISIM, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLO + " (" + HASTA_TC + " TEXT UNIQUE NOT NULL, " + HASTA_SIFRE + " TEXT NOT NULL, " + HASTA_ADSOYAD + " TEXT NOT NULL, " + HASTA_TELEFON + " TEXT NOT NULL);");
         }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLO);
            onCreate(db);
        }
    }
}

