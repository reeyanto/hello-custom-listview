package com.riyanto.hellocustomlistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.riyanto.hellocustomlistview.adapter.MahasiswaAdapter;
import com.riyanto.hellocustomlistview.model.Mahasiswa;
import com.riyanto.hellocustomlistview.repository.MahasiswaRepo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvMahasiswa;
    TextView tvDataEmpty;
    FloatingActionButton fabAdd;

    MahasiswaRepo mahasiswaRepo;
    List<Mahasiswa> mahasiswaList;
    MahasiswaAdapter mahasiswaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMahasiswa = findViewById(R.id.lv_mahasiswa);
        tvDataEmpty = findViewById(R.id.tv_data_empty);
        fabAdd = findViewById(R.id.fab_add);

        mahasiswaList    = new ArrayList<>();
        mahasiswaRepo    = new MahasiswaRepo(mahasiswaList);
        mahasiswaAdapter = new MahasiswaAdapter(this, mahasiswaList);
        lvMahasiswa.setAdapter(mahasiswaAdapter);

        checkVisibility();

        fabAdd.setOnClickListener(view -> showFormAdd().show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.options_clear) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Konfirmasi");
            builder.setCancelable(false);
            builder.setMessage("Apakah yakin ingin menghapus semua data?");
            builder.setPositiveButton("Ya", (dialogInterface, i) -> {
                mahasiswaRepo.clearAll();
                //mahasiswaAdapter.notifyDataSetChanged();

                checkVisibility();
            });
            builder.setNegativeButton("Tidak", (dialogInterface, i) -> {
                // tidak melakukan apa-apa
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private AlertDialog showFormAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.form_add, null);
        EditText etNim = view.findViewById(R.id.et_nim);
        EditText etNama = view.findViewById(R.id.et_nama);
        EditText etProdi = view.findViewById(R.id.et_prodi);
        Button btnSave = view.findViewById(R.id.btn_save);

        builder.setView(view);
        AlertDialog dialog = builder.create();

        dialog.setOnShowListener(dialogInterface -> {
            etNim.requestFocus();
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        });

        btnSave.setOnClickListener(view1 -> {
            Mahasiswa mahasiswa = new Mahasiswa();

            mahasiswa.setNim(etNim.getText().toString());
            mahasiswa.setNama(etNama.getText().toString());
            mahasiswa.setProdi(etProdi.getText().toString());

            mahasiswaRepo.save(mahasiswa);
            //mahasiswaAdapter.notifyDataSetChanged();
            dialog.dismiss();

            checkVisibility();
        });

        return dialog;
    }

    private void checkVisibility() {
        if (mahasiswaRepo.getAll().size() == 0) {
            lvMahasiswa.setVisibility(View.GONE);
            tvDataEmpty.setVisibility(View.VISIBLE);
        } else {
            lvMahasiswa.setVisibility(View.VISIBLE);
            tvDataEmpty.setVisibility(View.GONE);
        }
    }

}