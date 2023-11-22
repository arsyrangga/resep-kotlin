package com.rangga.resepin.ui.screen.onboarding

import android.app.ActionBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rangga.resepin.R
import com.rangga.resepin.ui.theme.primary
import com.rangga.resepin.ui.theme.white
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, actionBar: ActionBar?) {


    LaunchedEffect(key1 = true) {
        delay(2000) // Delay for 2 seconds
        actionBar?.show()
        navController.navigate("home_screen") // Navigate to the main screen
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = primary),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.app_name), color = white, fontSize = 36.sp)
    }
}