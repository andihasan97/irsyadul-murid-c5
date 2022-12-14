package com.andihasan7.irsyadulmuridc5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone
import java.util.Locale
import android.widget.Toast
import com.andihasan7.irsyadulmuridc5.databinding.ActivityMainBinding
import com.andihasan7.irsyadulmuridc5.Konversi.Konversi
import com.andihasan7.irsyadulmuridc5.IrsyadulMurid.IrsyadulMurid

class MainActivity : AppCompatActivity() {
    
    // mengaktifkan viewBinding
    private lateinit var binding: ActivityMainBinding
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        
        handler = Handler()
        
        // Tetapkan tugas Runnable untuk dieksekusi setiap detik
        runnable = object : Runnable {
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
                
                
                val i = IrsyadulMurid(date, bulanS, year)
        
                binding.hasilImsakMain.text = i.getImsak()
                binding.hasilShubuhMain.text = i.getShubuh()
                binding.hasilTerbitMain.text = i.getThulu()
                binding.hasilDluhaMain.text = i.getDluha()
                binding.hasilDzuhurMain.text = i.getDzuhur()
                binding.hasilAsharMain.text = i.getAshar()
                binding.hasilMaghribMain.text = i.getMaghrib()
                binding.hasilIsyaMain.text = i.getIsya()
                binding.hasilTengahmalamMain.text = i.getTengahMalam()
                binding.hasilRashdul1Main.text = i.getRashdul1()
                binding.hasilRashdul2Main.text = i.getRashdul2()
                binding.hasilDekRealMain.text = i.getDeklinasi()
                binding.hasilEquationOfTimeMain.text = i.getEquationOfTime()
                binding.hasilAzimuthBuMain.text = i.getQiblatBU()
                binding.hasilAzimuthUtsbMain.text = i.getQiblatUTSB()
                
                // Toast.makeText(this@MainActivity, "Anda mendapatkan nilai $date $bulanS $year", Toast.LENGTH_SHORT).show()
        

                // Tetapkan tanggal yang diformat ke TextView Menjadwal ulang tugas Runnable untuk dieksekusi dalam 100 milidetik
                handler.postDelayed(this, 100)
            }
        }
        
        // jalankan runnable
        handler.post(runnable)
        
        
        binding.btnCustomHisab.setOnClickListener {
            val intent = Intent(this@MainActivity, CustomHisabActivity::class.java)
            startActivity(intent)
        }
        
        binding.btnAbout.setOnClickListener {
            val intent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(intent)
        }
        
    }
    
    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 100) // jalankan runnable
    }

    
    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

}