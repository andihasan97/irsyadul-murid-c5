package com.andihasan7.irsyadulmuridc5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andihasan7.irsyadulmuridc5.databinding.ActivityCustomHisabBinding

class CustomHisabActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityCustomHisabBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomHisabBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}