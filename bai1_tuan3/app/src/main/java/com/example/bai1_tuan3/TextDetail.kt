package com.example.bai1_tuan3


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon

import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bai1_tuan3.ui.theme.Bai1_tuan3Theme

class TextDetail : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val titleIntent = intent.getStringExtra("title") ?: ""
            val detailIntent = intent.getStringExtra("detail")?: ""
            Bai1_tuan3Theme {
                TextDetailDisplay(titleIntent,detailIntent)
            }

        }

    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    fun TextDetailDisplay(title: String ="Default title",detail: String= "Default detail"){
        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Text(text = "Text Detail",
                        fontSize = 24.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF2196F3),
                        modifier = Modifier.padding(start = 90.dp)

                    )

                },
                navigationIcon = {
                    IconButton(onClick = {finish()}) {
                        Icon(
                            modifier = Modifier.size(44.dp),
                            tint = Color(0xFF2196F3),
                            painter = painterResource(id = R.drawable.chevron_left),
                            contentDescription = "Back")
                    }
                }
            )

        } )
        { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
                


        ) {
                TitleDisplay(title,detail,Modifier) }
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


}