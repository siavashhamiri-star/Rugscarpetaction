package com.example.farshbazar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.farshbazar.data.local.FarshBazarDatabase
import com.example.farshbazar.data.repository.FarshBazarRepository
import com.example.farshbazar.ui.navigation.MainAppNavigation
import com.example.farshbazar.ui.theme.FarshBazarTheme
import com.example.farshbazar.ui.viewmodel.MainViewModel
import com.example.farshbazar.ui.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels {
        val database = FarshBazarDatabase.getDatabase(applicationContext)
        val repository = FarshBazarRepository(database.farshBazarDao())
        MainViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FarshBazarTheme {
                MainAppNavigation(viewModel = viewModel)
            }
        }
    }
}
