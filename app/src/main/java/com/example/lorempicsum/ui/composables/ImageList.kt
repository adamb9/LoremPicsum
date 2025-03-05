package com.example.lorempicsum.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lorempicsum.entity.ImageEntity

@Composable
fun ImageList(images: List<ImageEntity>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(images) { image ->
            ImageCard(image)
        }
    }
}