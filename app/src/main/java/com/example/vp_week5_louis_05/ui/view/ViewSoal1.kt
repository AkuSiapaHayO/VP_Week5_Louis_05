package com.example.vp_week5_louis_05.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.vp_week5_louis_05.viewmodel.ViewModelSoal1

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewSoal1() {
    ViewSoal1(ViewModelSoal1())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewSoal1(viewModelSoal1: ViewModelSoal1) {

    val gameState by viewModelSoal1.uiState.collectAsState()
    var guessNumber by rememberSaveable { mutableStateOf("") }
    var showDialog by rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Text(
            text = "Guess The Number",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 36.dp)
        ){
            Column (
                modifier = Modifier
                    .padding(20.dp),
            ){
                Text(
                    text = "Number of guesses : ${gameState.guess}",
                    modifier = Modifier
                        .align(End)
                )

                Text(
                    text = "${gameState.number}",
                    modifier = Modifier
                        .align(CenterHorizontally)
                )
                Text(
                    text = "Score : ${gameState.score}",
                    modifier = Modifier
                        .align(CenterHorizontally)
                )
                OutlinedTextField(
                    value = guessNumber,
                    onValueChange = { number ->
                        guessNumber = number
                    }
                )
            }
        }
        Button(
            onClick = {
                showDialog = viewModelSoal1.isGameOver(guessNumber)
            },

            ) {
            Text(text = "Submit")
        }
    }

    if (showDialog) {
        Box(
            modifier = Modifier
                .background(Color.Gray.copy(alpha = 0.5f))
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Column {
                    Text(text = "Welp!")
                    Text(text = "You scored: ${gameState.score} ")
                    Button(
                        onClick = {
                            showDialog = false
                            viewModelSoal1.reset()
                        }
                    ) {
                        Text(text = "Reset")
                    }
                }
            }
        }
    }
}