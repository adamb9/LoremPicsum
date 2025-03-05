package com.example.lorempicsum.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lorempicsum.data.api.ImageApiServiceImpl
import com.example.lorempicsum.repo.ImageRepoImpl
import com.example.lorempicsum.ui.composables.DropdownMenuFilterRow
import com.example.lorempicsum.ui.composables.ImageList
import kotlinx.coroutines.runBlocking

@Composable
fun ImageListScreen() {

    val apiService = ImageApiServiceImpl()
    val repo = ImageRepoImpl(apiService)
    val imageList = runBlocking {
        repo.getImageList(1, 30)
    }
    val authorNames = imageList.map { it.author }.distinct()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        DropdownMenuFilterRow(authorNames)
        HorizontalDivider(color = Color.LightGray, thickness = 2.dp)

        ImageList(imageList)
    }
}