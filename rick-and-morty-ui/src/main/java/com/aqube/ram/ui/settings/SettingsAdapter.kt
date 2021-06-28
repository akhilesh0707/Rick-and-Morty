package com.aqube.ram.ui.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aqube.ram.R
import com.aqube.ram.base.BaseAdapter
import com.aqube.ram.databinding.ItemSettingListBinding
import com.aqube.ram.domain.models.SettingType
import com.aqube.ram.domain.models.Settings
import com.aqube.ram.extension.makeGone
import com.aqube.ram.extension.makeVisible
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.CornerFamily.ROUNDED
import javax.inject.Inject

private const val TOP = 0
private const val MIDDLE = 1
private const val BOTTOM = 2

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
        return SettingsViewHolder(binding, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TOP
            list.size - 1 -> BOTTOM
            else -> MIDDLE
        }
    }

    inner class SettingsViewHolder(
        private val binding: ItemSettingListBinding,
        private val viewType: Int
    ) :
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
                            isChecked = item.selectedValue
                            setOnCheckedChangeListener { _, isChecked ->
                                item.selectedValue = isChecked
                                setClickListener(item)
                            }
                        }
                        textViewValue.makeGone()
                    }
                }
                when (viewType) {
                    TOP -> setRadius(binding.cardViewRoot, true)
                    BOTTOM -> setRadius(binding.cardViewRoot, false)
                }
            }
        }

        private fun setClickListener(settings: Settings) {
            onItemClickListener?.let { itemClick ->
                itemClick(settings)
            }
        }

        private fun setRadius(cardView: MaterialCardView, isTop: Boolean) {
            val radius: Float = cardView.context.resources.getDimension(R.dimen.card_view_radius)
            val shareAppendable = cardView.shapeAppearanceModel.toBuilder().apply {
                if (isTop) {
                    setTopLeftCorner(ROUNDED, radius)
                    setTopRightCorner(ROUNDED, radius)
                } else {
                    setBottomRightCorner(ROUNDED, radius)
                    setBottomLeftCorner(ROUNDED, radius)
                }
            }.build()
            cardView.shapeAppearanceModel = shareAppendable
        }
    }
}
