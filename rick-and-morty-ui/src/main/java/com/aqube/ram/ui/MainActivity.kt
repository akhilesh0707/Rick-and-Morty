package com.aqube.ram.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.aqube.ram.R
import com.aqube.ram.databinding.ActivityMainBinding
import com.aqube.ram.extension.showSnackBar
import com.aqube.ram.presentation.viewmodel.CharacterListViewModel
import com.aqube.ram.presentation.viewmodel.CharacterState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: CharacterListViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    private var backPressedOnce = false
    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setObservers()
        initNavigationController()
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
                Log.d("TAG=============>", characterState.characters.toString())
            }
            is CharacterState.Error -> {
            }
            CharacterState.Init -> {
            }
            CharacterState.Loading -> {
            }
        }
    }

    private fun initNavigationController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigationHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.characterListFragment,
                R.id.characterListFragment,
                R.id.characterListFragment
            )
        )
        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        if (navController.graph.startDestination == navController.currentDestination?.id) {
            if (backPressedOnce) {
                super.onBackPressed()
                return
            }

            backPressedOnce = true
            showSnackBar(binding.rootView, getString(R.string.app_exit_label), false)

            activityScope.launch {
                delay(2000)
                backPressedOnce = false
            }
        } else {
            super.onBackPressed()
        }
    }
}