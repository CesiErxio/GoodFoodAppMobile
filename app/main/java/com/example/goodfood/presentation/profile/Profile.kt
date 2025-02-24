package com.example.goodfood.presentation.profile

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.goodfood.R
import com.example.goodfood.presentation.profile.components.ProfileCard
import com.example.goodfood.presentation.util.Screen

@Composable
fun Profile(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel(),
) {

    val firstName by viewModel.firstName.collectAsState(initial = "")
    val lastName by viewModel.lastName.collectAsState(initial = "")
    val email by viewModel.email.collectAsState(initial = "")
    val userType by viewModel.userType.collectAsState(initial = "")

    val context = LocalContext.current as Activity

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            IconButton(onClick = { navController.navigateUp() }) {Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = stringResource(R.string.back)
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(16.dp, 0.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
            ) {
                Text(
                    text = "$firstName $lastName",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = email,
                    modifier = Modifier.alpha(0.5f),
                )

            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Image(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = stringResource(R.string.display_picture)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(R.string.edit),
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.clickable {
                            Toast
                                .makeText(context, R.string.coming_soon, Toast.LENGTH_SHORT)
                                .show()
                        },
                        fontWeight = FontWeight.Bold
                    )
                }
            }


        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            color = Color.Black
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            ProfileCard(
                title = stringResource(R.string.profile_informations),
                subtext = stringResource(R.string.profile_informations_subtitle),
                onClick = {
                    navController.navigate(Screen.ProfileInformations.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                modifier = Modifier.width(200.dp),
                onClick = {
                    viewModel.onEvent(ProfileEvent.PerformLogout {
                        navController.navigate(Screen.LoginScreen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                inclusive = true
                            }
                        }
                    })
                }
            ) {
                Text(
                    text = stringResource(R.string.log_out),
                    fontSize = 16.sp,
                )
            }
        }
    }
}

