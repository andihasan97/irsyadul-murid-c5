package com.andihasan7.irsyadulmuridc5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.andihasan7.irsyadulmuridc5.databinding.ActivityCustomHisabBinding

class CustomHisabActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityCustomHisabBinding
    
    // Buat variabel bulan sebagai variabel global
    private var bulan: Int = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomHisabBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        
        

        // Membuat array dengan nama bulan
        val namaBulan = arrayOf("Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")

        // Membuat ArrayAdapter dengan nama bulan sebagai data
        val adapter = ArrayAdapter(this, R.layout.spinner_item, namaBulan)
        
        val spinner = binding.spnInputBulan


        // Menetapkan ArrayAdapter sebagai adapter untuk spinner
        spinner.adapter = adapter

        // Menambahkan listener untuk spinner
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Mengambil index bulan yang dipilih
                bulan = position + 1//.toString()
                // Menampilkan index bulan yang dipilih di console
                

                
                // test dengan | Toast.makeText(this@CustomHisabActivity, "Anda mendapatkan nilai $hasil", Toast.LENGTH_SHORT).show()


            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
        
        binding.btnHitung.setOnClickListener {
            val inputTanggal = binding.edtInputTanggal.text.toString().trim()
            val inputTahun = binding.edtInputTahun.text.toString().trim()

            var isEmptyFields = false

            if (inputTanggal.isEmpty()) {
                isEmptyFields = true
                
                binding.edtInputTanggal.error = "Field ini tidak boleh kosong"
            }
            if (inputTahun.isEmpty()) {
                isEmptyFields = true
                
                binding.edtInputTahun.error = "Field ini tidak boleh kosong"
            }

            if (!isEmptyFields) {
                val hasil = inputTanggal.toInt() + inputTahun.toInt() + bulan
                
                Toast.makeText(this@CustomHisabActivity, "Anda mendapatkan nilai $hasil", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
}