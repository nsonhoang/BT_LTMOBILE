package com.example.bai1_tuan4


import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.bai1_tuan4.ui.theme.Blue
import com.google.firebase.auth.FirebaseAuth



@Composable
fun Profile(){
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()


    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxSize()

        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .clickable {
                        val intent = Intent(context, Login::class.java)
                        context.startActivity(intent)
                    }
                    .border(width = 1.dp, color = Color.LightGray),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Image(
                        painter = painterResource(R.drawable.baseline_person_24),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )
                }
                Spacer(
                    modifier = Modifier.width(10.dp)
                )
                Text(
                    text = if (auth.currentUser != null) auth.currentUser?.displayName ?: "Đã đăng nhập" else "Chưa đăng nhập" ,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    color = Blue
                )
            }
        }
    }
}
