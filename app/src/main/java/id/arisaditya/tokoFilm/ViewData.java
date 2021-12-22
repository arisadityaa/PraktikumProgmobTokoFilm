package id.arisaditya.tokoFilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity {
    private DBMember MyDatabase;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList UsernameList;
    private ArrayList NamaList;
    private ArrayList TelponList;
    private ArrayList JKList;
    private ArrayList GenreList;
    private ArrayList PresentaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        UsernameList = new ArrayList<>();
        NamaList = new ArrayList<>();
        TelponList = new ArrayList<>();
        JKList = new ArrayList<>();
        GenreList = new ArrayList<>();
        PresentaseList = new ArrayList<>();

        MyDatabase = new DBMember(getBaseContext());
        recyclerView = findViewById(R.id.recycle);
        getData();

        //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(UsernameList, NamaList, TelponList, JKList, GenreList, PresentaseList);

        //Memasang Adapter pada RecyclerView
        recyclerView.setAdapter(adapter);

        //Membuat Underline pada Setiap Item Didalam List
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    //Berisi Statement-Statement Untuk Mengambi Data dari Database
    @SuppressLint("Recycle")
    protected void getData(){
        //Mengambil Repository dengan Mode Membaca
        SQLiteDatabase ReadData = MyDatabase.getReadableDatabase();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM "+ DBMember.MyColumns.NamaTabel,null);

        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal

        //Melooping Sesuai Dengan Jumlan Data (Count) pada cursor
        for(int count=0; count < cursor.getCount(); count++){

            cursor.moveToPosition(count);//Berpindah Posisi dari no index 0 hingga no index terakhir

            UsernameList.add(cursor.getString(0));
            NamaList.add(cursor.getString(1));
            TelponList.add(cursor.getString(2));
            JKList.add(cursor.getString(3));
            GenreList.add(cursor.getString(4));
            PresentaseList.add(cursor.getString(5));
        }
    }
}