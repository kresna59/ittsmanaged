package com.example.helloandroid.frontend

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.drawable.Icon
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.helloandroid.R
import com.example.helloandroid.data.ListMeetData
import com.example.helloandroid.data.ListMeetDataWrapper
import com.example.helloandroid.respon.ApiRespon
import com.example.helloandroid.respon.ListMeetRespond
import com.example.helloandroid.service.ListMeetInterface
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Calendar



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun eventmeet(navController: NavController, baseUrl: String) {
    var nameState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var eventState by remember { mutableStateOf("") }
    var zoomState by remember { mutableStateOf("") }
    var dateState by remember { mutableStateOf("") }



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Event Meet") },
            )
        },
        content = { innerPadding ->
            LazyColumn(contentPadding = innerPadding, modifier = Modifier.padding(16.dp)) {
                item {
                    OutlinedTextField(
                        value = nameState,
                        onValueChange = { nameState = it },
                        label = { Text("Nama Pembuat") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    OutlinedTextField(
                        value = eventState,
                        onValueChange = { eventState = it },
                        label = { Text("Nama Event") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    OutlinedTextField(
                        value = emailState,
                        onValueChange = { emailState = it },
                        label = { Text("email pembuat") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    OutlinedTextField(
                        value = zoomState,
                        onValueChange = { zoomState = it },
                        label = { Text("link invitation") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    val mContext = LocalContext.current
                    val mCalendar = Calendar.getInstance()
                    val mYear = mCalendar.get(Calendar.YEAR)
                    val mMonth = mCalendar.get(Calendar.MONTH)
                    val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

                    OutlinedTextField(
                        value = dateState,
                        onValueChange = { /* This field is read-only */ },
                        label = { Text("Date") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                DatePickerDialog(mContext, { _, year, month, dayOfMonth ->
                                    dateState = "$dayOfMonth/${month+1}/$year"
                                }, mYear, mMonth, mDay).show()
                            },
                        readOnly = true
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Button(
                        onClick = {
                            val data = ListMeetDataWrapper(
                                ListMeetData(
                                    nameState = nameState,
                                    eventState = eventState,
                                    emailState = emailState,
                                    zoomState = zoomState,
                                    dateState = dateState,

                                )
                            )
                            val retrofit = Retrofit.Builder()
                                .addConverterFactory(GsonConverterFactory.create())
                                .baseUrl(baseUrl)
                                .build()
                                .create(ListMeetInterface::class.java)
                            val postListMeet = retrofit.addList(data)
                            postListMeet.enqueue(
                                object : Callback<ApiRespon<ListMeetRespond>>{
                                    override fun onResponse(
                                        call: Call<ApiRespon<ListMeetRespond>>,
                                        response: Response<ApiRespon<ListMeetRespond>>
                                    ) {
                                        if(response.isSuccessful){
                                            navController.navigate("testpage")
                                        }else{
                                            println(response.errorBody())
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<ApiRespon<ListMeetRespond>>,
                                        t: Throwable
                                    ) {
                                        println(t)
                                    }
                                }
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Submit")
                    }
                }
            }
        }
    )
}
