package com.example.bai1_tuan3

import MainViewModel
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


import com.example.bai1_tuan3.ui.theme.Bai1_tuan3Theme



class UiComponentList : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Bai1_tuan3Theme {

            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayUiComponents(onTextDetail:(String,String)->Unit,onHome:()-> Unit) {
    val viewModel: MainViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.fetchTasks()
    }
    val data = viewModel.tasks.collectAsState().value
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
                    Text(text = "Text Detail",
                        fontSize = 24.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF2196F3),
                        modifier = Modifier.padding(start = 90.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {onHome() }) { //xu li
                        Icon(
                            modifier = Modifier.size(44.dp),
                            tint = Color(0xFF2196F3),
                            painter = painterResource(id = R.drawable.chevron_left),
                            contentDescription = "Back"
                        )
                    }
                }
            )

        } ) { innerPadding ->
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)) {
                items(data) { taskData ->
                    ListComponents(taskData.id,taskData.title, taskData.description, onTextDetail = { title, detail -> onTextDetail(title, detail) }, modifier = Modifier)
                    Spacer(modifier = Modifier.height(30.dp))
                    taskData.subtasks.forEach{}
                }
            }
        }
    }
}



@Composable
fun ListComponents(id:Int,title1: String,detail :String,onTextDetail: (String,String) -> Unit,modifier: Modifier){
    Column(
    ) {

        Spacer(modifier = Modifier.height(5.dp))
        Component(id,title1,detail,onTextDetail = { title1, detail -> onTextDetail(title1, detail)},modifier=Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(5.dp))


    }
}

@Composable
fun Component(id: Int,title: String,detail :String,onTextDetail: (String,String) -> Unit,modifier: Modifier){
    val context = LocalContext.current
    Card(
        modifier=Modifier.fillMaxWidth()
            .clickable {onTextDetail(title,detail)}

    ) {
        Box(
            modifier = Modifier
                .background(color = Color(0xFF2196F3))
                .fillMaxWidth()
                .padding(20.dp)

        ) {
            Column {
                Text(text = title,
                    fontWeight = FontWeight(700),
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Text(text = detail,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }

        }

    }

}