package com.example.helloandroid.frontend

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun listevent(navController: NavController) {
    var nameState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var eventState by remember { mutableStateOf("") }
    var zoomState by remember { mutableStateOf("") }
    var dateState by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Event Meet", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = eventState,
            onValueChange = { eventState = it },
            label = { Text("Nama Event") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = emailState,
            onValueChange = { emailState = it },
            label = { Text("email pembuat") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = zoomState,
            onValueChange = { zoomState = it },
            label = { Text("link invitation") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        val mContext = LocalContext.current
        val mCalendar = Calendar.getInstance()
        val mYear = mCalendar.get(Calendar.YEAR)
        val mMonth = mCalendar.get(Calendar.MONTH)
        val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

        Button(onClick = {
            DatePickerDialog(mContext, { _, year, month, dayOfMonth ->
                dateState = "$dayOfMonth/${month+1}/$year"
            }, mYear, mMonth, mDay).show()
        }) {
            Text("Select Date")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Handle form submission here */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}
