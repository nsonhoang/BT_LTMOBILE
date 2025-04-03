package com.example.bai1_tuan3


import MainViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon

import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bai1_tuan3.ui.theme.Bai1_tuan3Theme
import kotlinx.coroutines.flow.collect

class TextDetail : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Bai1_tuan3Theme {
            }
        }

    }
}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TextDetailDisplay(title: String ="Default Name",detail: String = "Default",goHome:()->Unit) {
        val viewModel: MainViewModel = viewModel()
        LaunchedEffect(Unit) {
            viewModel.fetchTaskData(1)
        }
        val data = viewModel.datatask.collectAsState().value
        if (viewModel.isLoading.collectAsState().value) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
        } else if (viewModel.errorMessage.collectAsState().value != null) {
            Text(text = "Error: ${viewModel.errorMessage.collectAsState().value}")
        } else {
            Scaffold(topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Text Detail",
                            fontSize = 24.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF2196F3),
                            modifier = Modifier.padding(start = 90.dp)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                        }) {
                            Icon(
                                modifier = Modifier.size(44.dp),
                                tint = Color(0xFF2196F3),
                                painter = painterResource(id = R.drawable.chevron_left),
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            },
                bottomBar = {
                    Row {
                        Button(
                            onClick = {
                                goHome()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF2196F3),
                                contentColor = Color.White,
                                disabledContainerColor = Color.LightGray,
                                disabledContentColor = Color.Gray),

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(text = "Go Home", fontSize = 24.sp, color = Color.White)
                        }
                    }
                }
            )
            { innerPadding ->
                LazyColumn(
                    modifier = Modifier.padding(innerPadding)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {
                    items(data) { it ->
                        TitleDisplay(it.title, it.id.toString(), modifier = Modifier)
                    }

                }
            }
        }
    }






    @Composable
    fun TitleDisplay(title :String,detail: String,modifier: Modifier){
        Column {
            Text(text = title,
                fontWeight = FontWeight(600),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier

                )
            Text(text = detail)
        }

    }


