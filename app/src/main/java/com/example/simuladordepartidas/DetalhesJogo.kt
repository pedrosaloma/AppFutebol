package com.example.simuladordepartidas

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simuladordepartidas.databinding.ActivityDetalhesJogoBinding
import com.example.simuladordepartidas.databinding.ActivityMainBinding

class DetalhesJogo : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesJogoBinding

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetalhesJogoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
    }
}