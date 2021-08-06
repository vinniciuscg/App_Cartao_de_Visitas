package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.myapplication.App
import com.example.myapplication.R
import com.example.myapplication.data.BusinessCard
import com.example.myapplication.databinding.ActivityAddNewCardBinding

class AddNewCard : AppCompatActivity() {

    private val binding by lazy {ActivityAddNewCardBinding.inflate(layoutInflater)}

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners(){
        binding.btnNewCard.setOnClickListener {
            val businessCard = BusinessCard(
                nome = binding.newCardName.editText?.text.toString(),
                telefone = binding.newCardPhone.editText?.text.toString(),
                email = binding.newCardEmail.editText?.text.toString(),
                empresa = binding.newCardCompany.editText?.text.toString(),
                corDeFundo = binding.newCardColor.editText?.text.toString()
            )

            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.add_card_success, Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.closeAddCard.setOnClickListener {
            finish()
        }
    }
}