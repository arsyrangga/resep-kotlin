package com.rangga.resepin.ui.screen.onboarding

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rangga.resepin.ui.components.BottomBar
import com.rangga.resepin.ui.components.CardComponent
import com.rangga.resepin.ui.data.Recipe
import com.rangga.resepin.ui.data.retrofit.ApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()

    val datas = remember {
        mutableStateListOf<Recipe>()
    }
    val loading = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    fun getDetail() {
        coroutineScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    loading.value = true
                    val response = ApiRequest.getApiService(context).getListRecipe().awaitResponse()
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.let {
                            it.results.map {
                                datas.add(Recipe(it.img, it.title, it.key))
                            }
                        }
                    }
                }
            } finally {
                loading.value = false
            }
        }
    }

    LaunchedEffect(Unit) {
        getDetail()
    }


//  Code
    Scaffold(bottomBar = { BottomBar(navController) }) {
        if (loading.value) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }


        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            modifier = Modifier.padding(it),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(datas) { data ->
                TextButton(
                    onClick = { navController.navigate("detail_screen/${data.key}") }
                ) {
                    CardComponent(data)
                }
            }
        }
    }
}


