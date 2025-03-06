package com.example.lorempicsum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

                ImageListScreen(viewModel)
            }
        }
    }
}
