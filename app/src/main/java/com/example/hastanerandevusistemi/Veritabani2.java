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

public class Veritabani2 {
    private static final String DATABASE_ISIM = "randevu2";
    private static final int DATABASE_VERSION = 1;
    private final Context con;
    private VeritabaniHelper veritabanihelper;
    private SQLiteDatabase veritabanim;

    private static final String DATABASE_TABLO = "randevular";
    public static final String RANDEVU_ID = "id";
    public static final String RANDEVU_HASTATC = "hastatc";
    public static final String RANDEVU_DOKTOR = "doktor";
    public static final String RANDEVU_BRANS = "brans";
    public static final String RANDEVU_TARIH = "tarih";
    public static final String RANDEVU_SAAT = "saat";


    public Veritabani2(Context c) {
        this.con = c;
    }
    public Veritabani2 baglantiyiAc() throws SQLException {
        veritabanihelper = new VeritabaniHelper(con);
        veritabanim = veritabanihelper.getWritableDatabase();
        return this;
    }
    public void baglantiyiKapat() {
        veritabanihelper.close();
    }
    public void randevuKayit(String hastatc,String doktor,String brans,String tarih,String saat){
        ContentValues val = new ContentValues();
        val.put("hastatc",hastatc);
        val.put("doktor",doktor);
        val.put("brans",brans);
        val.put("tarih",tarih);
        val.put("saat",saat);
        veritabanim.insert("randevular",null,val);
    }

    public void silRandevuKayit (String id) {
        veritabanim.delete("randevular","id=?",new String[]{id});
    }

    public List<String> doktorListele(){
        List<String> veriler = new ArrayList<String>();
        veritabanim  = veritabanihelper.getReadableDatabase();
        try {
            String[] sutunlar = {RANDEVU_ID,RANDEVU_HASTATC,RANDEVU_DOKTOR,RANDEVU_BRANS,RANDEVU_TARIH,RANDEVU_SAAT};
            Cursor cursor = veritabanim.query(DATABASE_TABLO, sutunlar,null,null,null,null,null);
            while (cursor.moveToNext()){
                veriler.add(cursor.getInt(0)
                        + " - "
                        + cursor.getString(1)
                        + " - "
                        + cursor.getString(2)
                        + " - "
                        + cursor.getString(3)
                        + " - "
                        + cursor.getString(4)
                        + " - "
                        + cursor.getString(5));
            }
        }catch (Exception e){
        }
        return veriler;
    }

    public ArrayList<HashMap<String, String>> randevular(){
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
        return randevulist;
    }

    public ArrayList<HashMap<String, String>> randevuGetir(String hastatc){
        veritabanim = veritabanihelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DATABASE_TABLO + " WHERE hastatc="+hastatc;
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
        return randevulist;
    }

    public void guncelleRandevu(String guncellenecekId, String hastaTcGuncelle, String doktorGuncelle, String bransGuncelle, String tarihGuncelle, String saatGuncelle) {
        ContentValues cvGuncelle = new ContentValues();
        cvGuncelle.put(RANDEVU_HASTATC, hastaTcGuncelle);
        cvGuncelle.put(RANDEVU_DOKTOR, doktorGuncelle);
        cvGuncelle.put(RANDEVU_BRANS, bransGuncelle);
        cvGuncelle.put(RANDEVU_TARIH, tarihGuncelle);
        cvGuncelle.put(RANDEVU_SAAT, saatGuncelle);
        veritabanim.update("randevular",cvGuncelle,"id=?",new String[]{guncellenecekId});
    }

    private static class VeritabaniHelper extends SQLiteOpenHelper {
        public VeritabaniHelper(Context contextim) {
            super(contextim, DATABASE_ISIM, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLO + " (" + RANDEVU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + RANDEVU_HASTATC + " TEXT NOT NULL, " + RANDEVU_DOKTOR + " TEXT NOT NULL, " + RANDEVU_BRANS + " TEXT NOT NULL, " + RANDEVU_TARIH + " TEXT NOT NULL, " + RANDEVU_SAAT + " TEXT NOT NULL);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLO);
            onCreate(db);
        }
    }
}
