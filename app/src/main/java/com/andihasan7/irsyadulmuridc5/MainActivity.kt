package com.andihasan7.irsyadulmuridc5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.andihasan7.irsyadulmuridc5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    // mengaktifkan viewBinding
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        
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