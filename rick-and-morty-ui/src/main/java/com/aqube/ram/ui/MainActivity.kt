package com.aqube.ram.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.aqube.ram.databinding.ActivityMainBinding
import com.aqube.ram.presentation.viewmodel.CharacterListViewModel
import com.aqube.ram.presentation.viewmodel.CharacterState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: CharacterListViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setObservers()
    }

    private fun setObservers() {
        viewModel.stateObservable.observe(this) { characterState ->
            updateView(characterState)
        }
        viewModel.getCharacters()
    }

    private fun updateView(characterState: CharacterState) {
        when (characterState) {
            is CharacterState.Success -> {
                //   binding.progressBar.makeGone()
                //  binding.textView.text = characterState.characters.toString()
            }
            is CharacterState.Error -> {
            }
            CharacterState.Init -> {
            }
            CharacterState.Loading -> {
            }
        }
    }
}