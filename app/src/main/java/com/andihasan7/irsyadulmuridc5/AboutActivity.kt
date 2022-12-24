package com.andihasan7.irsyadulmuridc5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andihasan7.irsyadulmuridc5.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityAboutBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}