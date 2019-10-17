package indrablake.com.directoryapps.data;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import indrablake.com.directoryapps.R;


/**
 * Created by indrablake on 17/10/2019.
 */

public class KontakAdapter extends BaseAdapter {

    Activity activity;
    LayoutInflater inflater;
    List<KontakData> items;

    public KontakAdapter(Activity activity, List<KontakData> items){

        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater == null){
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null) {
            assert inflater != null;
            convertView = inflater.inflate(R.layout.list_item_row,null);
        }
        //get view data kontak
        TextView id           = convertView.findViewById(R.id.id);
        TextView nama = convertView.findViewById(R.id.nama);
        TextView bagian = convertView.findViewById(R.id.bagian);
        TextView alamat = convertView.findViewById(R.id.alamat);
        TextView notelp = convertView.findViewById(R.id.notelp);

        //ambil data
        KontakData kontak = items.get(position);
        //adding data to views
        nama.setText(kontak.getNama());
        bagian.setText(kontak.getBagian());
        alamat.setText(kontak.getNotelp());
        notelp.setText(kontak.getAlamat());

        return convertView;
    }
}
