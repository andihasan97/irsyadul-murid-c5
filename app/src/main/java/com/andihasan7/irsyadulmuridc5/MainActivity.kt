package com.andihasan7.irsyadulmuridc5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone
import java.util.Locale
import com.andihasan7.irsyadulmuridc5.databinding.ActivityMainBinding
import com.andihasan7.irsyadulmuridc5.Konversi.Konversi

class MainActivity : AppCompatActivity() {
    
    // mengaktifkan viewBinding
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        
        val handler = Handler()
        
        // Tetapkan tugas Runnable untuk dieksekusi setiap detik
        handler.post(object : Runnable {
            override fun run() {
                // Dapatkan waktu saat ini
                val currentTime = Calendar.getInstance(TimeZone.getDefault()).time
                val c = Calendar.getInstance()

                // Format waktu menggunakan SimpleDateFormat
                val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                val formattedTime = timeFormat.format(currentTime)

                // 
                binding.tvTimeMain.text = formattedTime

                // Setel waktu yang diformat ke TextView
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val date = c.get(Calendar.DATE)
                
                val hariPasaran = Konversi().getHariPasaran(date, month, year)
                
                val bulanS = month + 1 // tambah 1 karena Sistem membaca dari index 0
	            val bulanString = when (bulanS) {
                        1 -> "Januari"
		                2 -> "Februari"
		                3 -> "Maret"
		                4 -> "April"
		                5 -> "Mei"
		                6 -> "Juni"
		                7 -> "Juli"
		                8 -> "Agustus"
		                9 -> "September"
		                10 -> "Oktober"
		                11 -> "November"
		                12 -> "Desember"
		                else -> "Ada yang salah"
                }
                
                val finalDate = "|  $hariPasaran, $date $bulanString $year"
                
                
                
                binding.tvDateMain.text = finalDate

                // Tetapkan tanggal yang diformat ke TextView Menjadwal ulang tugas Runnable untuk dieksekusi dalam 100 milidetik
                handler.postDelayed(this, 100)
            }
        })
        
        
        binding.btnCustomHisab.setOnClickListener {
            val intent = Intent(this@MainActivity, CustomHisabActivity::class.java)
            startActivity(intent)
        }
        
        binding.btnAbout.setOnClickListener {
            val intent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(intent)
        }
        
    }
}