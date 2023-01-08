package com.andihasan7.irsyadulmuridc5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.andihasan7.irsyadulmuridc5.databinding.ActivityCustomHisabBinding
import com.andihasan7.irsyadulmuridc5.IrsyadulMurid.IrsyadulMurid

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
                

                // test dengan | Toast.makeText(this@CustomHisabActivity, "Anda mendapatkan nilai $hasil", Toast.LENGTH_SHORT).show()


            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Blok untuk tidak melakukan apapun
            }
        }
        
        binding.btnHitung.setOnClickListener {
            var inputTanggal = binding.edtInputTanggal.text.toString().trim()
            
            var inputTahun = binding.edtInputTahun.text.toString().trim()
            

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
                
                // jika tidak error, disini blok untuk mengeksekusi ketika tombol Hitung ditekan
                // val hasil = inputTanggal.toInt() + inputTahun.toInt() + bulan
                
                val i = IrsyadulMurid(inputTanggal.toInt(), bulan, inputTahun.toInt())
                
                binding.hasilImsakCustom.text = i.getImsak()
                binding.hasilShubuhCustom.text = i.getShubuh()
                binding.hasilTerbitCustom.text = i.getThulu()
                binding.hasilDluhaCustom.text = i.getDluha()
                binding.hasilDzuhurCustom.text = i.getDzuhur()
                binding.hasilAsharCustom.text = i.getAshar()
                binding.hasilMaghribCustom.text = i.getMaghrib()
                binding.hasilIsyaCustom.text = i.getIsya()
                binding.hasilTengahmalamCustom.text = i.getTengahMalam()
                binding.hasilRashdul1Custom.text = i.getRashdul1()
                binding.hasilRashdul2Custom.text = i.getRashdul2()
                binding.hasilDeklinasiCustom.text = i.getDeklinasi()
                binding.hasilEotCustom.text = i.getEquationOfTime()
                binding.hasilAzimuthBuCustom.text = i.getQiblatBU()
                binding.hasilAzimuthUtsbCustom.text = i.getQiblatUTSB()
                
                // Toast.makeText(this@CustomHisabActivity, "Anda mendapatkan nilai $inputTanggal.toInt() $bulan $inputTahun.toInt()", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
}