package com.example.vp_week5_louis_05.ui.view

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vp_week5_louis_05.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSoal2(){
    ViewSoal2()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewSoal2(){
    var sks by rememberSaveable { mutableStateOf("") }
    var score by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    Column (
        Modifier
            .padding(top = 24.dp, start = 16.dp, end = 16.dp)
    ){
        Text(
            text = "Courses",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Total SKS: ",
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "IPK: ",
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            OutlinedTextField(
                value = sks,
                onValueChange = {inputSKS ->
                    sks = inputSKS
                },
                modifier = Modifier
                    .weight(1f),
                placeholder = {
                    Text(
                        text = "SKS"
                    )
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = score,
                onValueChange = {inputScore ->
                    score = inputScore
                },
                modifier = Modifier
                    .weight(1f),
                placeholder = {
                    Text(
                        text = "Score"
                    )
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                value = name,
                onValueChange = {inputName ->
                    name = inputName
                },
                modifier = Modifier
                    .weight(0.6f),
                placeholder = {
                    Text(
                        text = "Name"
                    )
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {  }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "Plus Icon",
                    Modifier
                        .padding(vertical = 6. dp)
                )
            }
        }
    }
}