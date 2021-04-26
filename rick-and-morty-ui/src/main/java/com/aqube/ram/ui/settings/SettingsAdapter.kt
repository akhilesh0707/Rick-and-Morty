package com.aqube.ram.ui.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aqube.ram.base.BaseAdapter
import com.aqube.ram.databinding.ItemSettingListBinding
import com.aqube.ram.domain.models.SettingType
import com.aqube.ram.domain.models.Settings
import com.aqube.ram.extension.makeGone
import com.aqube.ram.extension.makeVisible
import javax.inject.Inject

class SettingsAdapter @Inject constructor() : BaseAdapter<Settings>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Settings>() {
        override fun areItemsTheSame(oldItem: Settings, newItem: Settings): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Settings, newItem: Settings): Boolean {
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
        RecyclerView.ViewHolder(binding.root), Binder<Settings> {
        override fun bind(item: Settings) {
            binding.apply {
                textViewSettingName.text = item.settingLabel
                when (item.type) {
                    SettingType.TEXT -> {
                        textViewValue.apply {
                            text = item.settingValue
                            makeVisible()
                            setOnClickListener {
                                setClickListener(item)
                            }
                        }

                        switchValue.makeGone()
                    }
                    SettingType.EMPTY -> {
                        root.setOnClickListener {
                            setClickListener(item)
                        }
                    }
                    SettingType.SWITCH -> {
                        switchValue.apply {
                            makeVisible()
                            isChecked = item.defaultValue
                            setOnCheckedChangeListener { _, isChecked ->
                                item.defaultValue = isChecked
                                setClickListener(item)
                            }
                        }
                        textViewValue.makeGone()
                    }
                }
            }
        }

        private fun setClickListener(settings: Settings) {
            onItemClickListener?.let { itemClick ->
                itemClick(settings)
            }
        }
    }
}