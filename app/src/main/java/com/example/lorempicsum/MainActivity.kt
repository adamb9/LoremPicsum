package com.example.lorempicsum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lorempicsum.data.api.ImageApiServiceImpl
import com.example.lorempicsum.repo.ImageRepoImpl
import com.example.lorempicsum.ui.composables.ImageList
import com.example.lorempicsum.ui.theme.LoremPicsumTheme
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val apiService = ImageApiServiceImpl()
    val repo = ImageRepoImpl(apiService)
    val imageList = runBlocking {
        repo.getImageList(1, 50)
    }

    ImageList(imageList)

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoremPicsumTheme {
        Greeting("Android")
    }
}