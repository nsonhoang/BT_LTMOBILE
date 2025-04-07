package com.example.bai1_tuan4

import UserViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bai1_tuan4.ui.theme.Bai1_tuan4Theme
import com.example.bai1_tuan4.ui.theme.Blue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bai1_tuan4Theme {
                BottomMyAppBar()
            }
        }
    }
}

@Preview
@Composable
fun BottomMyAppBar(){
    val navigationController = rememberNavController()
    val context = LocalContext.current
    val select = remember {
        mutableStateOf(Icons.Default.Home)
    }
    Scaffold(
        topBar = {
            if (select.value == Icons.Default.Home) MyTopBar()
        },
        floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        val intent = Intent(context, AddTask::class.java)

                        context.startActivity(intent)

                    },
                    shape = CircleShape,
                    containerColor = Blue,
                    modifier = Modifier
                        .offset(y = (50.dp))

                ) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
                }
        },
        floatingActionButtonPosition = FabPosition.Center,

        bottomBar = {
           androidx.compose.material3.BottomAppBar(
               containerColor = Color.Transparent,
               ) {
               Card(modifier = Modifier
                   .fillMaxHeight(),
                   shape = RoundedCornerShape(50.dp),
                   colors = CardColors(Color.White, Color.White, Color.DarkGray, Color.Gray)
               ) {
                   Row(modifier = Modifier
                       .fillMaxHeight(),
                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       IconButton(onClick = {
                           select.value = Icons.Default.Home
                           navigationController.navigate(Screens.Home.screen){
                               popUpTo(0)
                           }

                       }, modifier = Modifier.weight(1f)) {
                           Icon(Icons.Default.Home, contentDescription = null, modifier = Modifier.size(26.dp),
                               tint = if (select.value == Icons.Default.Home) Blue else Color.Gray)
                       }
                       IconButton(onClick = {
                           select.value = Icons.Default.DateRange
                           navigationController.navigate(Screens.Search.screen){
                               popUpTo(0)
                           }

                       }, modifier = Modifier.weight(1f)) {
                           Icon(Icons.Default.DateRange, contentDescription = null, modifier = Modifier.size(26.dp),
                               tint = if (select.value == Icons.Default.DateRange) Blue else Color.Gray)
                       }

                       IconButton(onClick = {
                           select.value = Icons.Default.Notifications
                           navigationController.navigate(Screens.Notification.screen){
                               popUpTo(0)
                           }

                       }, modifier = Modifier.weight(1f)) {
                           Icon(Icons.Default.Notifications, contentDescription = null, modifier = Modifier.size(26.dp),
                               tint = if (select.value == Icons.Default.Notifications) Blue else Color.Gray)
                       }
                       IconButton(onClick = {
                           select.value = Icons.Default.Settings
                           navigationController.navigate(Screens.Profile.screen){
                               popUpTo(0)
                           }
                       }, modifier = Modifier.weight(1f)) {
                           Icon(Icons.Default.Settings, contentDescription = null, modifier = Modifier.size(26.dp),
                               tint = if (select.value == Icons.Default.Settings) Blue else Color.Gray)
                       }
                   }
               }
           }
        }
    ) { innerPadding->
        NavHost(navController = navigationController,
            startDestination = Screens.Home.screen,
            modifier = Modifier.padding( innerPadding )
            ){
            composable(Screens.Home.screen){ Home() }
            composable(Screens.Search.screen){ Search() }
            composable(Screens.Notification.screen){ Notification() }
            composable(Screens.Profile.screen){ Profile() }
            composable("addtask") { TaskScreen() }
        }
    }
}
