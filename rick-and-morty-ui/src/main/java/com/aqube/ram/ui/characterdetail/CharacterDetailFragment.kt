package com.aqube.ram.ui.characterdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.aqube.ram.R
import com.aqube.ram.base.BaseFragment
import com.aqube.ram.databinding.FragmentCharacterDetailBinding
import com.aqube.ram.domain.models.Character
import com.aqube.ram.extension.observe
import com.aqube.ram.presentation.utils.Resource
import com.aqube.ram.presentation.utils.Resource.Status.*
import com.aqube.ram.presentation.viewmodel.BaseViewModel
import com.aqube.ram.presentation.viewmodel.CharacterDetailViewModel
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint

class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailBinding, BaseViewModel>() {

    override val layoutId: Int = R.layout.fragment_character_detail

    override val viewModel: CharacterDetailViewModel by viewModels()

    private val args: CharacterDetailFragmentArgs by navArgs()

    @Inject
    lateinit var glide: RequestManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.character, ::onViewStateChange)
        viewModel.getCharacterDetail(args.characterId)
        setUiChangeListeners()
    }

    private fun setUiChangeListeners() {
        viewBinding.checkBoxBookmark.setOnCheckedChangeListener { view, isChecked ->
            if (isChecked)
                viewModel.setUnBookmarkCharacter(view.tag.toString().toLong())
            else
                viewModel.setBookmarkCharacter(view.tag.toString().toLong())
        }
    }

    private fun onViewStateChange(result: Resource<Character>) {
        when (result.status) {
            SUCCESS -> {
                handleLoading(false)
                result.data?.let { character ->
                    viewBinding.apply {
                        viewBinding.textViewCharacterName.text = character.name
                        glide.load(character.image).into(imageViewCharacter)
                        viewBinding.checkBoxBookmark.isChecked = character.isBookMarked
                        viewBinding.checkBoxBookmark.tag = character.id
                    }
                }
            }
            ERROR -> {
                val error = result.message ?: "Error"
                handleErrorMessage(error)
            }
            LOADING -> {
                handleLoading(true)
            }
        }
    }
}