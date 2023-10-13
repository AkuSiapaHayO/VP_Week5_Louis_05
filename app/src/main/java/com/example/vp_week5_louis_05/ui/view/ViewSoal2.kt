package com.example.vp_week5_louis_05.ui.view

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vp_week5_louis_05.R
import com.example.vp_week5_louis_05.model.UIState2
import com.example.vp_week5_louis_05.viewmodel.ViewModelSoal2

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSoal2() {
    ViewSoal2(ViewModelSoal2())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewSoal2(viewModelSoal2: ViewModelSoal2) {
    val courseState by viewModelSoal2.uiState.collectAsState()
    var sks by remember { mutableStateOf("") }
    var score by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var isSKSError by remember { mutableStateOf(false) }
    var isScoreError by remember { mutableStateOf(false) }
    var isNameError by remember { mutableStateOf(false) }
    Column(
        Modifier
            .padding(top = 24.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = "Courses",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Total SKS: ${(viewModelSoal2.totalSKS())}",
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "IPK: ${"%.2f".format(viewModelSoal2.totalIPK())}",
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row {
            OutlinedTextField(
                value = sks,
                onValueChange = { inputSKS ->
                    sks = inputSKS
                },
                modifier = Modifier
                    .weight(1f),
                label = {
                    Text(
                        text = "SKS"
                    )
                },
                isError = isSKSError,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color(0xFF495D91),
                    focusedLabelColor = Color(0xFF495D91),
                    containerColor = Color.White
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = score,
                onValueChange = { inputScore ->
                    score = inputScore
                },
                modifier = Modifier
                    .weight(1f),
                label = {
                    Text(
                        text = "Score"
                    )
                },
                isError = isScoreError,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color(0xFF495D91),
                    focusedLabelColor = Color(0xFF495D91),
                    containerColor = Color.White
                )
            )
        }
        Row(
            Modifier
                .padding(top = 2.dp, start = 6.dp)
        ) {
            Text(
                text = if (isSKSError) {
                    "Invalid SKS"
                } else {
                    ""
                },
                Modifier
                    .weight(1f),
                Color.Red,
                12.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = if (isScoreError) {
                    "Invalid Score"
                } else {
                    ""
                },
                Modifier
                    .weight(1f),
                Color.Red,
                12.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { inputName ->
                    name = inputName
                },
                modifier = Modifier
                    .weight(0.6f),
                label = {
                    Text(
                        text = "Name"
                    )
                },
                isError = isNameError,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color(0xFF495D91),
                    focusedLabelColor = Color(0xFF495D91),
                    containerColor = Color.White
                )
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    isSKSError = !viewModelSoal2.checkSKS(sks)
                    isScoreError = !viewModelSoal2.checkScore(score)
                    isNameError = !viewModelSoal2.checkName(name)
                    if (viewModelSoal2.checkSKS(sks) && viewModelSoal2.checkScore(score) && viewModelSoal2.checkName(
                            name
                        )
                    ) {
                        viewModelSoal2.addCourse(sks, score, name)
                        sks = ""
                        score = ""
                        name = ""
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF495D91)
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "Plus Icon",
                    Modifier
                        .padding(vertical = 6.dp)
                )
            }
        }
        if (isNameError) {
            Text(
                text = "Please fill your name",
                Modifier
                    .padding(top = 2.dp, start = 6.dp),
                Color.Red,
                12.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(courseState) { course ->
                CourseCard(course, viewModelSoal2)
            }
        }
    }
}

@Composable
fun CourseCard(course: UIState2, viewModelSoal2: ViewModelSoal2) {
    Card(
        Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE0E1EB)
        )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 32.dp, top = 12.dp, bottom = 12.dp),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Name: ${course.name}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    letterSpacing = 0.4.sp
                )
                Text(
                    text = "SKS: ${course.sks}",
                    fontSize = 15.sp
                )
                Text(
                    text = "Score: ${course.score}",
                    fontSize = 15.sp
                )
            }
            Image(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Delete Icon",
                Modifier
                    .clickable {
                        viewModelSoal2.deleteCourse(course)
                    }
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}