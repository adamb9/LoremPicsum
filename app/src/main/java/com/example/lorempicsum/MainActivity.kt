package com.example.lorempicsum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.lorempicsum.ui.screens.imageListScreen.ImageListScreen
import com.example.lorempicsum.ui.screens.imageListScreen.ImageListViewModel
import com.example.lorempicsum.ui.theme.LoremPicsumTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoremPicsumTheme {
                val viewModel = koinViewModel<ImageListViewModel>()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        ImageListScreen(viewModel)
                    }
                }
            }
        }
    }
}
