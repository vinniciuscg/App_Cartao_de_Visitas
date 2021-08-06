package com.example.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myapplication.App
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private val adapter by lazy {BusinessCardAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerMain.adapter = adapter
        getAllBusinessCards()
        setListeners()
    }

    private fun setListeners(){
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNewCard::class.java)
            startActivity(intent)
        }
        //Implementar adpter click
    }

    private fun getAllBusinessCards(){
        mainViewModel.getAll().observe(this, {businessCards ->
            adapter.submitList(businessCards)
        })
    }
}