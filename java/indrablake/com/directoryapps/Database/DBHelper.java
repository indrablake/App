package indrablake.com.directoryapps.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by indrablake on 18/04/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "directory.db";

    public static final String TABLE_SQLite = "kontak";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "nama";
    public static final String COLUMN_BAGIAN = "bagian";
    public static final String COLUMN_ADDRESS = "alamat";
    public static final String COLUMN_TELP = "telp";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_SQLite + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_BAGIAN + " TEXT NOT NULL, " +
                COLUMN_ADDRESS + " TEXT NOT NULL, " +
                COLUMN_TELP + " TEXT NOT NULL " +
                ")";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLite);
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>>kontakList;
        kontakList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_SQLite;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_NAME, cursor.getString(1));
                map.put(COLUMN_BAGIAN, cursor.getString(2));
                map.put(COLUMN_ADDRESS, cursor.getString(3));
                map.put(COLUMN_TELP, cursor.getString(4));
                kontakList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select sqlite ", "" + kontakList);

        database.close();
        return kontakList;
    }

    public void insert(String nama, String bagian, String alamat, String telp) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_SQLite + " (nama, bagian, alamat, telp) " +
                "VALUES ('" + nama + "', '" + bagian + "', '" + alamat + "', '" + telp + "')";

        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id, String nama, String bagian, String alamat, String telp) {
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "UPDATE " + TABLE_SQLite + " SET " + COLUMN_NAME + "='" + nama + "', " + COLUMN_BAGIAN + "='" + bagian + "', "
                + COLUMN_ADDRESS + "='" + alamat + "', "
                + COLUMN_TELP + "='" + telp + "' "
                + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_SQLite + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}
