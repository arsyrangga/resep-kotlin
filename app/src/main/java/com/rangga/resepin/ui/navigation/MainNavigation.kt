package com.rangga.resepin.ui.navigation

import android.app.ActionBar
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rangga.resepin.ui.screen.onboarding.AboutScreen
import com.rangga.resepin.ui.screen.detail.DetailScreen
import com.rangga.resepin.ui.screen.onboarding.HomeScreen
import com.rangga.resepin.ui.screen.onboarding.SplashScreen

@Composable
fun ResepinNavigation(actionBar: ActionBar?) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController, actionBar)
        }
        composable("home_screen") {
            HomeScreen(navController)
        }
        composable("about_screen") {
            AboutScreen(navController)
        }

        composable("detail_screen/{key}") {
            val key = it.arguments?.getString("key")
            DetailScreen(navController, key.toString())
        }


    }
}