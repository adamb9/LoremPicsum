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
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ImageListViewModel()

        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(
                //interactionModule, networkModule, presentationModule, repositoryModule
            )
        }


        //enableEdgeToEdge()
        setContent {
            LoremPicsumTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        ImageListScreen(viewModel)
                    }
                }
            }
        }
    }
}
