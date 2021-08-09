package com.example.cartaodevisitas.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.example.cartaodevisitas.App
import com.example.cartaodevisitas.R
import com.example.cartaodevisitas.data.BusinessCard
import com.example.cartaodevisitas.databinding.ActivityAddNewCardBinding

class AddNewCardActivity : AppCompatActivity() {

    private val binding by lazy {ActivityAddNewCardBinding.inflate(layoutInflater)}

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    private var colorHasChanged: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val colors = resources.getStringArray(R.array.business_card_colors)
        val adapterColors = ArrayAdapter(this, R.layout.dropdown_item, colors)
        binding.autocompleteColors.setAdapter(adapterColors)

        setListeners()
    }

    private fun setListeners(){
        if(!colorHasChanged){
            binding.newCardMenuColor.boxStrokeColor = Color.parseColor("#FFFFFF")
        }
        binding.btnNewCard.setOnClickListener {
            val businessCard = BusinessCard(
                nome = binding.newCardName.editText?.text.toString(),
                telefone = binding.newCardPhone.editText?.text.toString(),
                email = binding.newCardEmail.editText?.text.toString(),
                empresa = binding.newCardCompany.editText?.text.toString(),
                corDeFundo = binding.newCardMenuColor.boxStrokeColor.toString()
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.add_card_success, Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.autocompleteColors.doAfterTextChanged {
            when (it.toString()){
                "Branco" -> binding.newCardMenuColor.boxStrokeColor = Color.parseColor("#FFFFFF")
                "Verde" -> binding.newCardMenuColor.boxStrokeColor = Color.parseColor("#A4E079")
                "Vermelho" -> binding.newCardMenuColor.boxStrokeColor = Color.parseColor("#FA5050")
                "Amarelo" -> binding.newCardMenuColor.boxStrokeColor = Color.parseColor("#FFFD85")
                "Laranja" -> binding.newCardMenuColor.boxStrokeColor = Color.parseColor("#FCA253")
                "Azul" -> binding.newCardMenuColor.boxStrokeColor = Color.parseColor("#69A6F5")
                "Cinza" -> binding.newCardMenuColor.boxStrokeColor = Color.parseColor("#BABABA")
            }
            colorHasChanged = true
        }

        binding.closeAddCard.setOnClickListener {
            finish()
        }
    }
}