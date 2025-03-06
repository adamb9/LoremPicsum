package com.example.lorempicsum.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lorempicsum.R

@Composable
fun DropdownMenuFilterRow(
    selectedAuthor: String?,
    names: List<String>,
    onFilterChanged: (String?) -> Unit
) {
    val isDropDownExpanded = remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .padding(16.dp, 8.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = stringResource(R.string.filter_label),
            modifier = Modifier.weight(1f)
        )
        Box(
            modifier = Modifier
                .padding(8.dp, 0.dp)
                .weight(4f)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        isDropDownExpanded.value = true
                    }
            ) {
                Text(text = selectedAuthor ?: "")
                Image(
                    painter = painterResource(id = R.drawable.drop_down_ic),
                    contentDescription = stringResource(R.string.dropdown_icon)
                )
            }
            DropdownMenu(
                expanded = isDropDownExpanded.value,
                onDismissRequest = {
                    isDropDownExpanded.value = false
                }
            ) {
                names.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it) },
                        onClick = {
                            isDropDownExpanded.value = false
                            onFilterChanged(it)
                        }
                    )
                }
            }
        }
        Button(
            onClick = {
                onFilterChanged(null)
            },
            modifier = Modifier.weight(2f)
        ) {
            Text(text = stringResource(R.string.clear))
        }

    }
}