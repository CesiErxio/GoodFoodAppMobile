package com.example.goodfood.presentation.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileCard(
    title: String,
    subtext: String,
    onClick: () -> Unit,
    dividerVisibility: Boolean = true
) {
    Column(
        modifier = Modifier
            .padding(32.dp, 0.dp)
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = title,
            fontSize = 20.sp
        )
        Text(
            text = subtext,
            modifier = Modifier.alpha(0.5f)
        )
        if (dividerVisibility) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

    }
}
