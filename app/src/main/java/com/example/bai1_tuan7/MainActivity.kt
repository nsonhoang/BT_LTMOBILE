package com.example.bai1_tuan7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bai1_tuan7.ui.theme.Bai1_tuan7Theme
import com.example.bai1_tuan7.ui.theme.Blue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bai1_tuan7Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(Modifier.padding(innerPadding)) // Pass the padding to MainScreen
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier) {
    var background by remember { mutableStateOf(Color.White) }

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .background(background), // Use the selected background color
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Setting",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Blue
        )
        Text(
            text = "Choosing the right theme sets the tone and personality of your app",
            color = Blue
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            ColorCard(color = Blue, onClick = { background = Blue })
            ColorCard(color = Color.Red, onClick = { background = Color.Red })
            ColorCard(color = Color.Black, onClick = { background = Color.Black })
            Spacer(modifier = Modifier.width(10.dp))
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Blue),
            onClick = {
                // Action when the button is clicked (if needed)
            }
        ) {
            Text(text = "Apply")
        }
    }
}

@Composable
fun ColorCard(color: Color, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = color),
        modifier = Modifier
            .width(100.dp)
            .height(50.dp)
            .clickable(onClick = onClick)
    ) {
    }
}


