package id.arisaditya.tokoFilm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList usernameList;
    private ArrayList namaList;
    private ArrayList telponList;
    private ArrayList jkList;
    private ArrayList genreList;
    private ArrayList presentaseList;
    private Context context;

    //Membuat Konstruktor pada Class RecyclerViewAdapter
    RecyclerViewAdapter(ArrayList idList, ArrayList namaList, ArrayList telponList, ArrayList jkList, ArrayList genreList, ArrayList presentaseList){
        this.usernameList = idList;
        this.namaList = namaList;
        this.telponList = telponList;
        this.jkList = jkList;
        this.genreList = genreList;
        this.presentaseList = presentaseList;
    }

    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView Username, Nama, Telpon, JenisKelamin, Genre, Presentase;
        private ImageButton Overflow;

        ViewHolder(View itemView) {
            super(itemView);

            //Mendapatkan Context dari itemView yang terhubung dengan Activity ViewData
            context = itemView.getContext();

            //Menginisialisasi View-View untuk digunakan pada RecyclerView
            Username = itemView.findViewById(R.id.UsernameView);
            Nama = itemView.findViewById(R.id.namaView);
            Telpon = itemView.findViewById(R.id.telponView);
            JenisKelamin = itemView.findViewById(R.id.jkView);
            Genre = itemView.findViewById(R.id.genreView);
            Presentase = itemView.findViewById(R.id.presentaseView);
            Overflow = itemView.findViewById(R.id.overflow);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Membuat View untuk Menyiapkan dan Memasang Layout yang Akan digunakan pada RecyclerView
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_design, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        //Memanggil Nilai/Value Pada View-View Yang Telah Dibuat pada Posisi Tertentu
        final String Username = usernameList.get(position).toString();
        final String Nama = namaList.get(position).toString();
        final String Telpon = telponList.get(position).toString();
        final String JenisKelamin = jkList.get(position).toString();
        final String Genre = genreList.get(position).toString();
        final String Presentase = presentaseList.get(position).toString();

        holder.Username.setText(Username);
        holder.Nama.setText(Nama);
        holder.Telpon.setText(Telpon);
        holder.JenisKelamin.setText(JenisKelamin);
        holder.Genre.setText(Genre);
        holder.Presentase.setText(Presentase);


        //Mengimplementasikan Menu Popup pada Overflow (ImageButton)
        holder.Overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //Membuat Instance/Objek dari PopupMenu
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.delete:
                                //Menghapus Data Dari Database
                                DBMember getDatabase = new DBMember(view.getContext());
                                SQLiteDatabase DeleteData = getDatabase.getWritableDatabase();
                                //Menentukan di mana bagian query yang akan dipilih
                                String selection = DBMember.MyColumns.USERNAME_MEMBER + " LIKE ?";
                                //Menentukan Nama Dari Data Yang Ingin Dihapus
                                String[] selectionArgs = {holder.Username.getText().toString()};
                                DeleteData.delete(DBMember.MyColumns.NamaTabel, selection, selectionArgs);

                                //Menghapus Data pada List dari Posisi Tertentu
                                int position = usernameList.indexOf(Username);
                                usernameList.remove(position);
                                notifyItemRemoved(position);
                                Toast.makeText(view.getContext(),"Data Dihapus",Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.update:
                                Intent dataForm = new Intent(view.getContext(), UpdateActivity.class);
                                dataForm.putExtra("SendUsername", holder.Username.getText().toString());
                                dataForm.putExtra("SendNama", holder.Nama.getText().toString());
                                dataForm.putExtra("SendTelpon", holder.Telpon.getText().toString());
                                context.startActivity(dataForm);
                                ((Activity)context).finish();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        //Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
        return usernameList.size();
    }



}
