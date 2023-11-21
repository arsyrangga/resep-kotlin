package com.rangga.resepin.ui.screen.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.rangga.resepin.R
import com.rangga.resepin.ui.components.BottomBar
import com.rangga.resepin.ui.data.Recipe
import com.rangga.resepin.ui.data.response.RecipeDetail
import com.rangga.resepin.ui.data.retrofit.ApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.awaitResponse


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, key: String) {
    val coroutineScope = rememberCoroutineScope()
    var datas by remember {
        mutableStateOf<RecipeDetail?>(null)
    }
    val context = LocalContext.current

    fun getDetail() {
        coroutineScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = ApiRequest.getApiService(context).getRecipeDetail(key).awaitResponse()
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.let {
                            datas = it
                            Log.d("TEH",datas.toString())
                        }
                    }
                }
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
            }
        }
    }

    LaunchedEffect(Unit) {
        getDetail()
    }
//  Code
    Scaffold(bottomBar = { BottomBar(navController) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
        ) {

                Image(
                    painter = rememberAsyncImagePainter(datas?.results?.image.toString()),
                    contentDescription = stringResource(
                        id = R.string.about_image
                    ),
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop,

                )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = datas?.results?.title.toString(), fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 8.dp))
                Text(text = datas?.results?.description.toString())
            }




        }
    }

}


