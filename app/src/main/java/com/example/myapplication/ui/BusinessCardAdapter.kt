package com.example.myapplication.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.BusinessCard
import com.example.myapplication.databinding.BusinessCardItemBinding

class BusinessCardAdapter:
    androidx.recyclerview.widget.ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallback()){

    var listenerShare: (View) -> Unit = {}

    inner class ViewHolder(
        private val binding: BusinessCardItemBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(item: BusinessCard){
            binding.businessCardName.text = item.nome
            binding.businessCardPhone.text = item.telefone
            binding.businessCardEmail.text = item.email
            binding.businessCardCompany.text = item.empresa
            binding.cardBody.setCardBackgroundColor(Color.parseColor(item.corDeFundo))
            binding.cardBody.setOnClickListener {
                listenerShare(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BusinessCardItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}



class DiffCallback : DiffUtil.ItemCallback<BusinessCard>(){
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard) =
        (oldItem == newItem)
    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard) =
        (oldItem.id == newItem.id)
}