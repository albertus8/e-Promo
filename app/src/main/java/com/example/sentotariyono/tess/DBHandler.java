package com.example.sentotariyono.tess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by albertus on 22/07/2016.
 */
public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "dbPromo";
    // Contacts table name
    private static final String TABLE_PROMO = "infoPromo";
    // Shops Table Columns names
    private static final String KEY_ID = "idPromo";
    private static final String KEY_NAME = "namaPromo";
    private static final String KEY_KATEGORI = "kategoriPromo";
    private static final String KEY_DESC = "deskripsiPromo";
    private static final String KEY_DATE = "tanggalPembuatan";
    private static final String KEY_DATA_IMG = "urlDataImage";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PROMO + "("
                + KEY_ID + " CHARACTER(6) PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_KATEGORI + " TEXT," + KEY_DESC + " TEXT," + KEY_DATE + " INTEGER," + KEY_DATA_IMG + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROMO);
        // Creating tables again
        onCreate(db);
    }

    // Adding new promo
    public void addPromo(GetSetData promo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, promo.getNamaPromo()); // Nama Promo
        values.put(KEY_KATEGORI, promo.getKategoriPromo()); // Kategori
        values.put(KEY_DESC, promo.getDeskripsiPromo()); // Deskripsi
        values.put(KEY_DATE, new Date().getTime()); // Tanggal buat
        values.put(KEY_DATA_IMG, promo.getUrlDataImage()); // String Image
        // Inserting Row
        db.insert(TABLE_PROMO, null, values);
        db.close(); // Closing database connection
    }

    // Getting one shop
    public GetSetData getShop(int id) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PROMO, new String[]{KEY_ID,KEY_NAME, KEY_KATEGORI, KEY_DESC, KEY_DATE, KEY_DATA_IMG}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        String dtStart = cursor.getString(4);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = format.parse(dtStart);

        GetSetData contact = new GetSetData(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), date, cursor.getString(5));
        // return shop
        return contact;
    }
}
