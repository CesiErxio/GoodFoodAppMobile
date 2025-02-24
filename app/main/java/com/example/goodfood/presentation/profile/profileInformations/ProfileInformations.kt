package com.example.goodfood.presentation.profile.profileInformations

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.goodfood.R
import com.example.goodfood.presentation.login.components.GoodFoodTextField
import com.example.goodfood.presentation.util.Screen

@Composable
fun ProfileInformations(
    navController: NavHostController,
    viewModel: ProfileInformationsViewModel = hiltViewModel(),
) {
    val email by viewModel.email
    val firstName by viewModel.firstName
    val lastName by viewModel.lastName
    val city by viewModel.city
    val phone by viewModel.phone

    val context = LocalContext.current as Activity

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = stringResource(R.string.back))
            }
        }
        Text(
            text = stringResource(R.string.modify), fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Prénom",
            fontSize = 16.sp
        )
        GoodFoodTextField(
            value = firstName.text,
            onValueChange = { viewModel.onEvent(ProfileInformationsEvent.EnteredFirstName(it)) },
            hint = firstName.hint
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Nom",
            fontSize = 16.sp
        )
        GoodFoodTextField(
            value = lastName.text,
            onValueChange = { viewModel.onEvent(ProfileInformationsEvent.EnteredLastName(it)) },
            hint = lastName.hint
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Ville",
            fontSize = 16.sp
        )
        GoodFoodTextField(
            value = city.text,
            onValueChange = { viewModel.onEvent(ProfileInformationsEvent.EnteredCity(it)) },
            hint = city.hint
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Numéro de téléphone",
            fontSize = 16.sp
        )
        GoodFoodTextField(
            value = phone.text,
            onValueChange = { viewModel.onEvent(ProfileInformationsEvent.EnteredPhone(it)) },
            hint = phone.hint
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Email",
            fontSize = 16.sp
        )
        GoodFoodTextField(
            value = email.text,
            onValueChange = { viewModel.onEvent(ProfileInformationsEvent.EnteredEmail(it)) },
            hint = email.hint
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(modifier = Modifier.width(200.dp), onClick = {
            viewModel.onEvent(ProfileInformationsEvent.PerformHome {
                navController.navigate(Screen.LoginScreen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                }
            })
        }) {
            Text(
                text = stringResource(R.string.modify),
                fontSize = 16.sp,
            )
        }

    }
}

