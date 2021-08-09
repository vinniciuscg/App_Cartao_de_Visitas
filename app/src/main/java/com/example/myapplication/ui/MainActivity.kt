package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.util.Image

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private val adapter by lazy {BusinessCardAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpPermissions()
        binding.recyclerMain.adapter = adapter
        registerForContextMenu(binding.recyclerMain)
        getAllBusinessCards()
        setListeners()
    }

    private fun setUpPermissions() {
        // write permissions to access storage
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1
        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )

    }

    private fun setListeners(){
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNewCardActivity::class.java)
            startActivity(intent)
        }
        adapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }
        adapter.listenerDelete = {
            deleteByName(it)
        }
    }

    private fun getAllBusinessCards(){
        mainViewModel.getAll().observe(this, {businessCards ->
            adapter.submitList(businessCards)
        })
    }

    private fun deleteByName(nome: String){
        mainViewModel.deleteByName(nome)
    }

}