package com.rangga.resepin.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rangga.resepin.R

data class BottomBarItem(val title: String, val icon: Painter, val nav: String)

@Composable
fun BottomBar(
    navController : NavController,
    modifier: Modifier = Modifier
) {


    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    var currentRoute by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = true) {
        currentRoute = currentBackStackEntry?.destination?.route.toString()
    }
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        val navigationItems = listOf(
            BottomBarItem(
                title = stringResource(R.string.beranda),
                icon = painterResource(id = R.drawable.home_icon),
                nav = "home_screen"
            ),
            BottomBarItem(
                title = stringResource(R.string.profile),
                icon = painterResource(id = R.drawable.profile_icon),
                nav = "about_screen"

            ),
        )
        navigationItems.map {
            NavigationBarItem(

                icon = {
                    Icon(
                        painter = it.icon,
                        contentDescription = it.title
                    )
                },
                label = {
                    Text(it.title)
                },
                selected = it.nav == currentRoute,
                onClick = {
                    navController.navigate(it.nav)
                }
            )
        }
    }
}