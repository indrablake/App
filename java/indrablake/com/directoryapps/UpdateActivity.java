package indrablake.com.directoryapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import indrablake.com.directoryapps.Database.DBHelper;


public class UpdateActivity extends AppCompatActivity {

    EditText txt_id, txt_name, txt_bagian,txt_address, txt_telp;
    Button btn_submit, btn_cancel;
    DBHelper SQLite = new DBHelper(UpdateActivity.this);
    String id, nama, bagian, alamat, telp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_id = (EditText) findViewById(R.id.txt_id);
        txt_name = (EditText) findViewById(R.id.txt_nama);
        txt_bagian = (EditText) findViewById(R.id.txt_bagian);
        txt_address = (EditText) findViewById(R.id.txt_alamat);
        txt_telp = (EditText) findViewById(R.id.txt_notelp);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        id = getIntent().getStringExtra(MainActivity.TAG_ID);
        nama = getIntent().getStringExtra(MainActivity.TAG_NAME);
        bagian = getIntent().getStringExtra(MainActivity.TAG_BAGIAN);
        alamat = getIntent().getStringExtra(MainActivity.TAG_ADDRESS);
        telp = getIntent().getStringExtra(MainActivity.TAG_TELP);

        if (id == null) {
            setTitle("Add Data");
        } else {
            setTitle("Edit Data");
            txt_id.setText(id);
            txt_name.setText(nama);
            txt_bagian.setText(bagian);
            txt_address.setText(alamat);
            txt_telp.setText(telp);
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (txt_id.getText().toString().equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e){
                    Log.e("Simpan", e.toString());
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank();
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Make blank all Edit Text
    private void blank() {
        txt_name.requestFocus();
        txt_id.setText(null);
        txt_name.setText(null);
        txt_bagian.setText(null);
        txt_telp.setText(null);
        txt_address.setText(null);

    }

    // Save data to SQLite database
    private void save() {
        if (String.valueOf(txt_name.getText()).equals("") || String.valueOf(txt_bagian.getText()).equals("")|| String.valueOf(txt_address.getText()).equals("")|| String.valueOf(txt_telp.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Anda belum memasukan Nama atau ALamat ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(txt_name.getText().toString().trim(),txt_bagian.getText().toString().trim(), txt_address.getText().toString().trim(), txt_telp.getText().toString().trim());
            Toast.makeText(getApplicationContext(),"Berhasil Menambahkan Data",Toast.LENGTH_SHORT).show();
            blank();
            finish();
        }
    }

    // Update data in SQLite database
    private void edit() {
        if (String.valueOf(txt_name.getText()).equals("") || String.valueOf(txt_bagian.getText()).equals("")|| String.valueOf(txt_address.getText()).equals("")|| String.valueOf(txt_telp.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Anda belum memasukan Nama atau ALamat ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(Integer.parseInt(txt_id.getText().toString().trim()),txt_name.getText().toString().trim(),
                    txt_bagian.getText().toString().trim(),
                    txt_address.getText().toString().trim(),
                    txt_telp.getText().toString().trim());
            Toast.makeText(getApplicationContext(),"Data berhasil di Ubah",Toast.LENGTH_SHORT).show();
            blank();
            finish();
        }
    }
}
