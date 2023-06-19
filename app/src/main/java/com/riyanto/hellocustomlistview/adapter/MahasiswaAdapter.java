package com.riyanto.hellocustomlistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.riyanto.hellocustomlistview.R;
import com.riyanto.hellocustomlistview.model.Mahasiswa;

import java.util.List;

public class MahasiswaAdapter extends BaseAdapter {

    Context context;
    List<Mahasiswa> mahasiswaList;

    public MahasiswaAdapter(Context context, List<Mahasiswa> mahasiswaList) {
        this.context = context;
        this.mahasiswaList = mahasiswaList;
    }

    @Override
    public int getCount() {
        return mahasiswaList.size();
    }

    @Override
    public Object getItem(int i) {
        return mahasiswaList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            view = inflater.inflate(R.layout.item, null);
        }

        TextView tvNim   = view.findViewById(R.id.tv_nim);
        TextView tvNama  = view.findViewById(R.id.tv_nama);
        TextView tvProdi = view.findViewById(R.id.tv_prodi);

        tvNim.setText(mahasiswaList.get(i).getNim());
        tvNama.setText(mahasiswaList.get(i).getNama());
        tvProdi.setText(mahasiswaList.get(i).getProdi());

        return view;
    }
}
