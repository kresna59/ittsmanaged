package com.example.helloandroid.frontend

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.helloandroid.PreferencesManager
import com.example.helloandroid.R
import com.example.helloandroid.respon.UserRespon
import com.example.helloandroid.service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController, context: Context = LocalContext.current) {
    val baseColor = Color(0xFF00676C)
    val cr1 = painterResource(id = R.drawable.logo)
    val cr2 = painterResource(id = R.drawable.logo)
    val cr3 = painterResource(id = R.drawable.logo)
    val konsul = painterResource(id = R.drawable.logo)
    val penitipan = painterResource(id = R.drawable.logo)
    val artikel = painterResource(id = R.drawable.logo)
    //var listUser: List<UserRespon> = remember
    var search by remember { mutableStateOf(TextFieldValue("")) }
    val preferencesManager = remember { PreferencesManager(context = context) }
    val listUser = remember { mutableStateListOf<UserRespon>() }
    //var listUser: List<UserRespon> by remember { mutableStateOf(List<UserRespon>()) }
    var baseUrl = "http://10.0.2.2:1337/api/"
    //var baseUrl = "http://10.217.17.11:1337/api/"
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserService::class.java)
    val call = retrofit.getData()
    call.enqueue(object : Callback<List<UserRespon>> {
        override fun onResponse(
            call: Call<List<UserRespon>>,
            response: Response<List<UserRespon>>
        ) {
            if (response.code() == 200) {
                //kosongkan list User terlebih dahulu
                listUser.clear()
                response.body()?.forEach { userRespon ->
                    listUser.add(userRespon)
                }
            } else if (response.code() == 400) {
                print("error login")
                var toast = Toast.makeText(
                    context,
                    "Username atau password salah",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun onFailure(call: Call<List<UserRespon>>, t: Throwable) {
            print(t.message)
        }

    })


    Scaffold(
        topBar = {

        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(18.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = search,
                onValueChange = { newText ->
                    search = newText
                },
                label = { Text(text = "Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp),
                trailingIcon = {
                    IconButton(onClick = {
                        // Handle the search action
                    }) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search",
                            tint = baseColor
                        )
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = baseColor,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = baseColor
                )
            )

            Box {
                Image(
                    painter = cr1,
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .height(200.dp)
                        .width(400.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    Box(
                        modifier = Modifier
                            .size(92.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = konsul,
                            contentDescription = null,
                            alignment = Alignment.Center,
                            modifier = Modifier
                                .height(70.dp)
                                .width(70.dp)
                                .clickable { navController.navigate("eventmeet") }
                        )
                    }

                    Text(text = "daftarkan event")
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    Box(
                        modifier = Modifier
                            .size(92.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = penitipan,
                            contentDescription = null,
                            alignment = Alignment.Center,
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp)
                                .clickable { navController.navigate("meetingroom") }
                        )
                    }

                    Text(text = "meeting room")
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    Box(
                        modifier = Modifier
                            .size(92.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = artikel,
                            contentDescription = null,
                            alignment = Alignment.Center,
                            modifier = Modifier
                                .height(70.dp)
                                .width(70.dp)
                                .clickable { navController.navigate("test2") }
                        )
                    }

                    Text(text = "list event")
                }
            }


            Box {
                Image(
                    painter = cr2,
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .height(200.dp)
                        .width(400.dp)
                )
            }

            Box {
                Image(
                    painter = cr3,
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .height(200.dp)
                        .width(400.dp)
                )
            }

        }
    }
}
