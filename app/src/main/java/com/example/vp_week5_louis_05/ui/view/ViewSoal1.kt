package com.example.vp_week5_louis_05.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Text(
            text = "Guess The Number",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE2E0EB)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 20.dp),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Number of guesses : ${gameState.guess}",
                    color = Color.White,
                    modifier = Modifier
                        .align(End)
                        .background(Color(0xFF4355B8), RoundedCornerShape(8.dp))
                        .padding(vertical = 4.dp, horizontal = 10.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "${gameState.number}",
                    modifier = Modifier
                        .align(CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "From 1 to 10 Guess the Number",
                    modifier = Modifier
                        .align(CenterHorizontally),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Score : ${gameState.score}",
                    modifier = Modifier
                        .align(CenterHorizontally),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = guessNumber,
                    onValueChange = { number ->
                        guessNumber = number
                    },
                    shape = RoundedCornerShape(12.dp),
                    label = {
                        Text(text = "Enter your number")
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedLabelColor = Color(0xFF4355B7),
                        focusedIndicatorColor = Color(0xFF4355B7),
                        containerColor = Color(0xFFE2E0EB)
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
            }
        }
        Button(
            onClick = {
                showDialog = viewModelSoal1.isGameOver(guessNumber)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4355B7)
            )
        ) {
            Text(
                text = "Submit"
            )
        }
    }

    if (showDialog) {
        Box(
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.75f))
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Column (
                    modifier = Modifier
                        .clip(RoundedCornerShape(32.dp))
                        .fillMaxWidth()
                        .background(Color(0xFFE9E8F7))
                        .padding(26.dp)
                ){
                    Text(
                        text = "Welp!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "You scored: ${gameState.score} ")
                    Spacer(modifier = Modifier.height(32.dp))
                    Row (
                        modifier = Modifier
                            .align(End)
                    ){
                        TextButton(
                            onClick = {
                                showDialog = false
                                viewModelSoal1.reset()
                            },
                        ) {
                            Text(
                                text = "Exit",
                                color = Color.Blue,
                                fontSize = 14.sp,
                            )

                        }
                        TextButton(
                            onClick = {
                                showDialog = false
                                viewModelSoal1.reset()
                            },
                        ) {
                            Text(
                                text = "Play Again",
                                color = Color.Blue,
                                fontSize = 14.sp,
                            )
                        }
                    }

                }
            }
        }
    }
}