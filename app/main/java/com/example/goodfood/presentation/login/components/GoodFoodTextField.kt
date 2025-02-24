package com.example.goodfood.presentation.login.components

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun GoodFoodTextField(
    value: String,
    onValueChange : (String) -> Unit,
    hint: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Next
    ),
    visualTransformation: VisualTransformation = VisualTransformation.None,

) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.width(280.dp),
        placeholder = {
            Text(
                text = hint,
                modifier = Modifier.alpha(0.5f)
            )
        },
        shape = RoundedCornerShape(9.dp),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        singleLine = true
    )
}