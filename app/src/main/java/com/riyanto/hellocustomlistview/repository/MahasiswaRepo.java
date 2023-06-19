package com.riyanto.hellocustomlistview.repository;

import com.riyanto.hellocustomlistview.model.Mahasiswa;

import java.util.List;

public class MahasiswaRepo {

    List<Mahasiswa> mahasiswaList;

    public MahasiswaRepo(List<Mahasiswa> mahasiswaList) {
        this.mahasiswaList = mahasiswaList;
    }

    public boolean save(Mahasiswa mahasiswa) {
        return mahasiswaList.add(mahasiswa);
    }

    public boolean delete(Mahasiswa mahasiswa) {
        return mahasiswaList.remove(mahasiswa);
    }
}
