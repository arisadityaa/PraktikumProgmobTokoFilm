package id.arisaditya.tokoFilm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    private DBMember MyDatabase;
    private TextView Username;
    private EditText NewNama, NewTelpon;
    private String getNewNama, getNewTelpon;
    private Button Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        MyDatabase = new DBMember(getBaseContext());
        Username = findViewById(R.id.usernameUpdateTv);
        NewNama = findViewById(R.id.namaUpdateEdit);
        NewTelpon = findViewById(R.id.telponUpdateEdit);

        //Menerima Data Nama dan NIK yang telah dipilih Oleh User untuk diposes
        NewNama.setText(getIntent().getExtras().getString("SendNama"));
        Username.setText(getIntent().getExtras().getString("SendUsername"));
        NewTelpon.setText(getIntent().getExtras().getString("SendTelpon"));

        Update = findViewById(R.id.btnUpdate);
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpdateData();
                startActivity(new Intent(UpdateActivity.this, ViewData.class));
                finish();
            }
        });
    }

    private void setUpdateData(){
        getNewNama = NewNama.getText().toString();
        getNewTelpon = NewTelpon.getText().toString();
        Intent intentMember = getIntent();
        String Username = intentMember.getExtras().getString("SendUsername");

        SQLiteDatabase database = MyDatabase.getReadableDatabase();

        if (getNewNama.length() == 0 || getNewTelpon.length() == 0){
            Toast.makeText(UpdateActivity.this,"Update Data Gagal",Toast.LENGTH_SHORT).show();
            Toast.makeText(UpdateActivity.this,"Data Tidak Boleh Kosong!",Toast.LENGTH_SHORT).show();
        }else{
            //Memasukan Data baru pada 2 kolom
            ContentValues values = new ContentValues();
            values.put(DBMember.MyColumns.NAMA_MEMBER, getNewNama);
            values.put(DBMember.MyColumns.TELPON_MEMBER, getNewTelpon);

            //Untuk Menentukan Data/Item yang ingin diubah berdasarkan NIK
            String selection = DBMember.MyColumns.USERNAME_MEMBER + " LIKE ?";
            String[] selectionArgs = {Username};
            database.update(DBMember.MyColumns.NamaTabel, values, selection, selectionArgs);
            Toast.makeText(getApplicationContext(), "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
        }

    }
}