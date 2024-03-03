package com.example.kasirlailaam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radiogroupPilih, radiogroupMember;
    private EditText etJumlah;
    private Button btnProses;
    private TextView tvBon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi
        radiogroupPilih = findViewById(R.id.radiogroupPilih);
        radiogroupMember = findViewById(R.id.radiogroupMember);
        btnProses = findViewById(R.id.btnProses);
        etJumlah = findViewById(R.id.etJumlah);
        tvBon = findViewById(R.id.tvBon);

        // btnProses
        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosesTransaksi();
            }
        });
    }

    private void prosesTransaksi() {
        // Mendapatkan pilihan item dari RadioGroup
        RadioButton selectedRadioButton = findViewById(radiogroupPilih.getCheckedRadioButtonId());

        // Pengecekan apakah ada RadioButton yang dipilih
        if (selectedRadioButton != null) {
            String itemName = selectedRadioButton.getText().toString();

            // Mendapatkan jumlah barang
            String jumlahText = etJumlah.getText().toString();
            if (!jumlahText.isEmpty()) {
                int jumlahBarang = Integer.parseInt(jumlahText);

                // Mendapatkan status membership
                boolean isMember = radiogroupMember.getCheckedRadioButtonId() == R.id.radioMember;

                // Harga barang
                int hargaBarang = getHargaBarang(itemName);

                // Biaya admin
                int biayaAdmin = getBiayaAdmin(itemName);

                // Diskon jika member
                double diskon = isMember ? 0.05 : 0;

                // Menghitung total harga
                double hargaBrg = hargaBarang * jumlahBarang;
                double diskonBarang = hargaBrg * diskon;
                double totalHarga = hargaBrg + biayaAdmin - diskonBarang;

                // Menampilkan elemen-elemen lainnya pada tampilan
                TextView tvNamaBarang = findViewById(R.id.tvNamaBarang);
                tvNamaBarang.setText("Nama Barang : " + itemName);

                TextView tvJumlahBarang = findViewById(R.id.tvJumlahBarang);
                tvJumlahBarang.setText("Jumlah Barang : " + jumlahBarang);

                TextView tvHargaBarang = findViewById(R.id.tvHargaBarang);
                tvHargaBarang.setText("Harga Barang : " + hargaBrg);

                TextView tvDiskon = findViewById(R.id.tvDiskon);
                tvDiskon.setText("Diskon : " + diskonBarang);

                TextView tvAdmin = findViewById(R.id.tvAdmin);
                tvAdmin.setText("Biaya Admin : " + biayaAdmin);

                TextView tvTotalBayar = findViewById(R.id.tvTotalBayar);
                tvTotalBayar.setText("Total Bayar : " + totalHarga);
            } else {
                // Tidak ada jumlah barang yang dimasukkan
                Toast.makeText(this, "Masukkan jumlah barang terlebih dahulu.", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Tidak ada RadioButton yang dipilih
            Toast.makeText(this, "Pilih satu item terlebih dahulu.", Toast.LENGTH_SHORT).show();
        }
    }

    private int getHargaBarang(String itemName) {
        switch (itemName) {
            case "Pulsa":
                return 50000;
            case "Token":
                return 20000;
            case "Emoney":
                return 100000;
            default:
                return 0;
        }
    }

    private int getBiayaAdmin(String itemName) {
        switch (itemName) {
            case "Pulsa":
                return 2000;
            case "Token":
                return 2100;
            case "Emoney":
                return 2500;
            default:
                return 0;
        }
    }
}
