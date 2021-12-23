package id.arisaditya.tokoFilm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public static final String USERNAME_MEMBER = "Username Member";
    public static final String NAMA_MEMBER = "Nama Member";
    public static final String TELPON_MEMBER = "Telpon Member";
    public static final String JK_MEMBER = "Jenis Kelamin Member";
    public static final String GENRE_MEMBER = "Genre Member";
    public static final String PRESENTASE_MEMBER = "Presentase Member";


    int seekbarValue, sJk;
    boolean isnull = false;
    TextView presentase;
    EditText username, nama, telepon;
    CheckBox action, drama, horor;
    RadioButton pria, wanita;
    RadioGroup jk;
    Button btnReset, btnSubmit;
    SeekBar seekbar1;
    String sUsername, sNama, sTelpon, sJenisKelamin, sGenre, sPresentase;

    DBMember dbMember;
    Button cancelDialog, kirimDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presentase = (TextView) findViewById(R.id.tvPresentase);

        username = (EditText) findViewById(R.id.etUsername);
        nama = (EditText) findViewById(R.id.etNama);
        telepon = (EditText) findViewById(R.id.etTelpon);

        action = (CheckBox) findViewById(R.id.checkAction);
        drama = (CheckBox) findViewById(R.id.checkDrama);
        horor = (CheckBox) findViewById(R.id.checkHoror);

        jk = (RadioGroup) findViewById(R.id.radioJK);
        pria = (RadioButton) findViewById(R.id.rd_pria);
        wanita = (RadioButton) findViewById(R.id.rd_wanita);

        btnReset = (Button) findViewById(R.id.btn_reset);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

        seekbar1 = (SeekBar) findViewById(R.id.seekbarFilm);

        seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekbarValue = i;
                String value =String.valueOf(i);
                if (value.equals("0")){
                    presentase.setText("Tidak Pernah");
                }else if (value.equals("1")){
                    presentase.setText("Jarang");
                }else if (value.equals("2")){
                    presentase.setText("Kadang - kadang");
                }else if (value.equals("3")){
                    presentase.setText("Sering");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isnull=isfilechecked();
                if(isnull){
                    sUsername = username.getText().toString();
                    sNama = nama.getText().toString();
                    sTelpon = telepon.getText().toString();

                    sJk = jk.getCheckedRadioButtonId();
                    if (sJk == pria.getId()){
                        sJenisKelamin = "Pria";
                    }else if(sJk == wanita.getId()){
                        sJenisKelamin = "Wanita";
                    }

                    sGenre="";
                    if(action.isChecked()){
                        sGenre = sGenre + "Action; ";
                    }
                    if(drama.isChecked()){
                        sGenre = sGenre + "Drama; ";
                    }
                    if(horor.isChecked()){
                        sGenre = sGenre + "Horor; ";
                    }

                    sPresentase = presentase.getText().toString();

                    shwDialog();

                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setText("");
                nama.setText("");
                telepon.setText("");
                pria.setChecked(false);
                wanita.setChecked(false);
                action.setChecked(false);
                drama.setChecked(false);
                horor.setChecked(false);
                seekbar1.setProgress(0);
                presentase.setText("");
                jk.setId(0);
            }
        });

        Button viewData = findViewById(R.id.btn_readData);
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ViewData.class));
            }
        });
    }

    private boolean isfilechecked(){
        if (username.length() == 0) {
            Toast.makeText(MainActivity.this,"Data tidak boleh kosong",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (nama.length() == 0) {
            Toast.makeText(MainActivity.this,"Data tidak boleh kosong",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (telepon.length() == 0) {
            Toast.makeText(MainActivity.this,"Data tidak boleh kosong",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (jk.getCheckedRadioButtonId() == -1) {
            Toast.makeText(MainActivity.this,"Harap pilih jenis kelamin",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!action.isChecked() && !drama.isChecked() && !horor.isChecked()) {
            Toast.makeText(MainActivity.this,"Harap pilih Genre",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void shwDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.alert_view);

        TextView alertUsername = dialog.findViewById(R.id.tUsername);
        TextView alertNama = dialog.findViewById(R.id.tNama);
        TextView alertTelpon = dialog.findViewById(R.id.tTelpon);
        TextView alertJK = dialog.findViewById(R.id.tJK);
        TextView alertGenre = dialog.findViewById(R.id.tGenre);
        TextView alertPresentase = dialog.findViewById(R.id.tPresentase);

        cancelDialog = (Button) dialog.findViewById(R.id.btn_cancel);
        kirimDialog = (Button) dialog.findViewById(R.id.btn_kirim);

        alertUsername.setText(sUsername);
        alertNama.setText(sNama);
        alertTelpon.setText(sTelpon);
        alertJK.setText(sJenisKelamin);
        alertGenre.setText(sGenre);
        alertPresentase.setText(sPresentase);

        cancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        kirimDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbMember = new DBMember(MainActivity.this);

                Intent intent_kirim = new Intent(MainActivity.this, secondActivity.class);
                SQLiteDatabase create = dbMember.getWritableDatabase();

                intent_kirim.putExtra(USERNAME_MEMBER, sUsername);
                intent_kirim.putExtra(NAMA_MEMBER, sNama);
                intent_kirim.putExtra(TELPON_MEMBER, sTelpon);
                intent_kirim.putExtra(JK_MEMBER, sJenisKelamin);
                intent_kirim.putExtra(GENRE_MEMBER, sGenre);
                intent_kirim.putExtra(PRESENTASE_MEMBER, sPresentase);

                //Membuat Map Baru, Yang Berisi Nama Kolom dan Data Yang Ingin Dimasukan
                ContentValues values = new ContentValues();
                values.put(DBMember.MyColumns.USERNAME_MEMBER, sUsername);
                values.put(DBMember.MyColumns.NAMA_MEMBER, sNama);
                values.put(DBMember.MyColumns.TELPON_MEMBER, sTelpon);
                values.put(DBMember.MyColumns.JK_MEMBER, sJenisKelamin);
                values.put(DBMember.MyColumns.GENRE_MEMBER, sGenre);
                values.put(DBMember.MyColumns.PRESENTASE_MEMBER, sPresentase);

                //Menambahkan Baris Baru, Berupa Data Yang Sudah Diinputkan pada Kolom didalam Database
                create.insert(DBMember.MyColumns.NamaTabel, null, values);

                startActivity(intent_kirim);
            }
        });
        dialog.show();
    }



}