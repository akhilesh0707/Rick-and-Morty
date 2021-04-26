package com.aqube.ram.ui.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aqube.ram.base.BaseAdapter
import com.aqube.ram.databinding.ItemSettingListBinding
import javax.inject.Inject

class SettingsAdapter @Inject constructor() : BaseAdapter<String>() {

    private val diffCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override val differ = AsyncListDiffer(this, diffCallback)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemSettingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingsViewHolder(binding)
    }

    inner class SettingsViewHolder(private val binding: ItemSettingListBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<String> {
        override fun bind(item: String) {
            binding.apply {
                textViewSettingName.text = item
            }
        }
    }
}