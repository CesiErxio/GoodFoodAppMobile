package com.example.goodfood.presentation.register

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.goodfood.R
import com.example.goodfood.presentation.login.UiEvent
import com.example.goodfood.presentation.login.components.GoodFoodTextField
import com.example.goodfood.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    navController: NavHostController,
) {

    val scaffoldState: ScaffoldState = rememberScaffoldState()

    val email by viewModel.email
    val password by viewModel.password
    val confirmPassword by viewModel.confirmPassword
    val firstName by viewModel.firstName
    val lastName by viewModel.lastName
    val city by viewModel.city
    val phone by viewModel.phone

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message, duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }
    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = stringResource(R.string.back))
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.register), fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Prénom",
                fontSize = 16.sp
            )
            GoodFoodTextField(
                value = firstName.text,
                onValueChange = { viewModel.onEvent(RegisterEvent.EnteredFirstName(it)) },
                hint = firstName.hint
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Nom",
                fontSize = 16.sp
            )
            GoodFoodTextField(
                value = lastName.text,
                onValueChange = { viewModel.onEvent(RegisterEvent.EnteredLastName(it)) },
                hint = lastName.hint
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ville",
                fontSize = 16.sp
            )
            GoodFoodTextField(
                value = city.text,
                onValueChange = { viewModel.onEvent(RegisterEvent.EnteredCity(it)) },
                hint = city.hint
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Numéro de téléphone",
                fontSize = 16.sp
            )
            GoodFoodTextField(
                value = phone.text,
                onValueChange = { viewModel.onEvent(RegisterEvent.EnteredPhone(it)) },
                hint = phone.hint
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Email",
                fontSize = 16.sp
            )
            GoodFoodTextField(
                value = email.text,
                onValueChange = { viewModel.onEvent(RegisterEvent.EnteredEmail(it)) },
                hint = email.hint
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Mot de passe",
                fontSize = 16.sp
            )
            GoodFoodTextField(
                value = password.text,
                onValueChange = { viewModel.onEvent(RegisterEvent.EnteredPassword(it)) },
                hint = password.hint,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Confirmer Mot de passe",
                fontSize = 16.sp
            )
            GoodFoodTextField(
                value = confirmPassword.text,
                onValueChange = { viewModel.onEvent(RegisterEvent.EnteredConfirmPassword(it)) },
                hint = confirmPassword.hint,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(modifier = Modifier.width(200.dp), onClick = {
                viewModel.onEvent(RegisterEvent.PerformRegister {
                    navController.navigate(Screen.LoginScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                    }
                })
            }) {
                Text(
                    text = stringResource(R.string.register),
                    fontSize = 16.sp,
                )
            }
        }
    }
}
