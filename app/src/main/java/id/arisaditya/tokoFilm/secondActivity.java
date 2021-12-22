package id.arisaditya.tokoFilm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class secondActivity extends AppCompatActivity {
    TextView tv_username, tv_nama, tv_telpon, tv_jk, tv_genre, tv_presentase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent_terima = getIntent();
        String Terima_username = intent_terima.getStringExtra(MainActivity.USERNAME_MEMBER);
        String Terima_nama = intent_terima.getStringExtra(MainActivity.NAMA_MEMBER);
        String Terima_telpon = intent_terima.getStringExtra(MainActivity.TELPON_MEMBER);
        String Terima_jk = intent_terima.getStringExtra(MainActivity.JK_MEMBER);
        String Terima_genre = intent_terima.getStringExtra(MainActivity.GENRE_MEMBER);
        String Terima_presentase = intent_terima.getStringExtra(MainActivity.PRESENTASE_MEMBER);

        tv_username = (TextView) findViewById(R.id.t_username);
        tv_nama = (TextView) findViewById(R.id.t_nama);
        tv_telpon = (TextView) findViewById(R.id.t_telpon);
        tv_jk = (TextView) findViewById(R.id.t_jk);
        tv_genre = (TextView) findViewById(R.id.t_genre);
        tv_presentase = (TextView) findViewById(R.id.t_presentase);

        tv_username.setText(Terima_username);
        tv_nama.setText(Terima_nama);
        tv_telpon.setText(Terima_telpon);
        tv_jk.setText(Terima_jk);
        tv_genre.setText(Terima_genre);
        tv_presentase.setText(Terima_presentase);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast messageText = Toast.makeText(getApplicationContext(), "Membuka Activity..", Toast.LENGTH_SHORT);
        messageText.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast messageText = Toast.makeText(getApplicationContext(), "Menutup Activity..", Toast.LENGTH_SHORT);
        messageText.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast messageText = Toast.makeText(getApplicationContext(), "Membuka Kembali Activity..", Toast.LENGTH_SHORT);
        messageText.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast messageText = Toast.makeText(getApplicationContext(), "Menutup Penuh Activity..", Toast.LENGTH_SHORT);
        messageText.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent_kembali = new Intent(this, MainActivity.class);
        Toast messageText = Toast.makeText(getApplicationContext(), "Kembali Ke Menu Awal", Toast.LENGTH_SHORT);
        messageText.show();
        startActivity(intent_kembali);
    }
}