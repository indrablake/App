package indrablake.com.directoryapps;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import indrablake.com.directoryapps.Database.DBHelper;
import indrablake.com.directoryapps.data.KontakAdapter;
import indrablake.com.directoryapps.data.KontakData;

public class MainActivity extends AppCompatActivity{

    ListView listView;
    String[] details;
    AlertDialog.Builder dialog;
    List<KontakData> itemList = new ArrayList<KontakData>();
    KontakAdapter adapter;
    DBHelper SQLite = new DBHelper(this);
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "nama";
    public static final String TAG_BAGIAN = "bagian";
    public static final String TAG_ADDRESS = "alamat";
    public static final String TAG_TELP = "telp";
    private static final int REQUEST_RESPONSE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        SQLite = new DBHelper(getApplicationContext());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.list_view);

        //info user
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("INFORMASI");
        builder.setMessage(R.string.info);
        builder.setCancelable(false);
        builder.setPositiveButton("Paham", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //kosong
            }
        });
        builder.show();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                startActivity(intent);
            }
        });

        adapter = new KontakAdapter(MainActivity.this, itemList);
        listView.setAdapter(adapter);

        // long press listview to show edit and delete
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub

                final String idx = itemList.get(position).getId();
                final String nama = itemList.get(position).getNama();
                final String bagian = itemList.get(position).getBagian();
                final String alamat = itemList.get(position).getAlamat();
                final String telp = itemList.get(position).getNotelp();

                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                                intent.putExtra(TAG_ID, idx);
                                intent.putExtra(TAG_NAME, nama);
                                intent.putExtra(TAG_BAGIAN, bagian);
                                intent.putExtra(TAG_ADDRESS, alamat);
                                intent.putExtra(TAG_TELP, telp);
                                startActivity(intent);
                                break;
                            case 1:
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("Anda yakin hapus??");
                                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        SQLite.delete(Integer.parseInt(idx));
                                        itemList.clear();
                                        getAllData();
                                        Toast.makeText(getApplicationContext(), "Data berhasil di hapus", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                builder.show();
                                break;
                            /*case 2:
                                Intent detail = new Intent(MainActivity.this, AboutActivity.class);
                                detail.putExtra("nama",nama);
                                startActivity(detail);
                                break;*/
                        }
                    }
                }).show();
                return false;
            }
        });

        getAllData();
    }

    private void getAllData() {
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();

        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(TAG_ID);
            String namas = row.get(i).get(TAG_NAME);
            String bagis = row.get(i).get(TAG_BAGIAN);
            String alam = row.get(i).get(TAG_ADDRESS);
            String telps = row.get(i).get(TAG_TELP);

            KontakData data = new KontakData();

            data.setId(id);
            data.setNama(namas);
            data.setBagian(bagis);
            data.setAlamat(alam);
            data.setNotelp(telps);

            itemList.add(data);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        getAllData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent about = new Intent(MainActivity.this,AboutActivity.class);
            startActivity(about);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
