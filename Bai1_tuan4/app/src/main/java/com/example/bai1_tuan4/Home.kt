package com.example.bai1_tuan4


import TaskData
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bai1_tuan4.ui.theme.Blue
import com.example.bai1_tuan4.ui.theme.LightBlue
import com.example.bai1_tuan4.ui.theme.LightGreen
import com.example.bai1_tuan4.ui.theme.LightRed

@Preview
@Composable
fun Home(){
    val viewModel: MainViewModel = viewModel()
    LaunchedEffect(Unit) {
        viewModel.fetchTasks()
    }
    val data = viewModel.tasks.collectAsState().value

    if(viewModel.isLoading.collectAsState().value){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }

    }else if (viewModel.errorMessage.collectAsState().value != null) {
       NoTasksYetScreen()
    }
    else{
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp)
        ) {
           LazyColumn {
               items(data){ item ->
                   TodoItem(item)
               }
           }
        }
    }
}

@Composable
fun TodoItem(item : TaskData){
    val colorItem : Color
    var  isChecked by remember { mutableStateOf(false) }
    val context = LocalContext.current
    when (item.priority){
        "High"->{colorItem= LightRed}
        "Medium"->{colorItem= LightBlue}
        else ->{colorItem= LightGreen}
    }
  Card(
      colors = CardDefaults.cardColors(containerColor = colorItem),
      modifier = Modifier
          .clickable {
              val i = Intent(context,TaskDetail::class.java)
              i.putExtra("id",item.id)
              context.startActivity(i)
          }
  ) {
      Column (
          modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp)
      ){
          Row(

          )
          {
              Checkbox(
                  checked = isChecked,
                  onCheckedChange = {
                      isChecked = !isChecked // Đảo ngược trạng thái
                  },
                  colors = CheckboxDefaults.colors(
                      checkedColor = Color.Black,
                  ),
                  modifier = Modifier
                      .padding(end = 10.dp)
              )
              Column {
                  Text(
                      text = item.title,
                      fontSize = 20.sp,
                      fontWeight = FontWeight(700)
                  )
                  Text(
                      text = item.description,
                      maxLines = 2,
                      overflow = TextOverflow.Ellipsis
                  )
              }
          }
          Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
          ) {
              Text(
                  text = "Status: "+ item.status,
                  fontWeight = FontWeight(700),
                  modifier = Modifier.padding(start = 12.dp)
              )
              Text(
                  text = item.createdAt
              )

          }
      }
  }
    Spacer(modifier = Modifier.height(10.dp))
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(){

    TopAppBar(
        title = {
            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
            ) {
                Text(
                    text = "SmartTasks",
                    color = Blue,
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600)
                )
                Text(
                    text = "A simple and efficient to-do app",
                    color = Blue,
                    fontSize = 12.sp
                )
            }
        },
        navigationIcon = {
            Image(
                painter = painterResource(R.drawable.uth_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Notifications, contentDescription = null, tint = Color(0xFFFFBE00))
            }
        }
    )
}

@Composable
fun NoTasksYetScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Thay thế bằng id hình ảnh của bạn
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No Tasks Yet!",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Stay productive—add something to do",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}