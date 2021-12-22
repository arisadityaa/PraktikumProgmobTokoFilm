package id.arisaditya.tokoFilm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBMember extends SQLiteOpenHelper {
    //InnerClass, untuk mengatur artibut seperti Nama Tabel, nama-nama kolom dan Query
    static abstract class MyColumns implements BaseColumns {
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "tb_members";
        static final String USERNAME_MEMBER = "username_member";
        static final String NAMA_MEMBER = "nama_member";
        static final String TELPON_MEMBER = "telpon_member";
        static final String JK_MEMBER = "jk_member";
        static final String GENRE_MEMBER = "genre_member";
        static final String PRESENTASE_MEMBER = "presentase_member";
    }

    private static final String NamaDatabse = "db_movie";
    private static final int VersiDatabase = 1;

    //Query yang digunakan untuk membuat Tabel
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+MyColumns.NamaTabel+
            "("+MyColumns.USERNAME_MEMBER+" TEXT PRIMARY KEY, "+MyColumns.NAMA_MEMBER+" TEXT NOT NULL, "+MyColumns.TELPON_MEMBER+
            " TEXT NOT NULL, "+MyColumns.JK_MEMBER+" TEXT NOT NULL, "+MyColumns.GENRE_MEMBER+
            " TEXT NOT NULL, "+MyColumns.PRESENTASE_MEMBER+" TEXT NOT NULL)";

    //Query yang digunakan untuk mengupgrade Tabel
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+MyColumns.NamaTabel;

    DBMember(Context context) {
        super(context, NamaDatabse, null, VersiDatabase);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }



}
