package com.example.bai1_tuan4

import TaskData
import android.R.attr.text
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bai1_tuan4.ui.theme.Blue
import java.util.UUID


class AddTask : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TaskScreen()
        }
    }
}

@Preview
@Composable

fun TaskScreen(){

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarAddTask()
        }
    ) {innerPadding ->

        AddTaskMain(Modifier.padding(innerPadding))

    }
}

@Composable
fun AddTaskMain(modifier: Modifier) {

    val context = LocalContext.current
    val viewModel: MainViewModel = viewModel()
    var text by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.fetchTasks()
    }
    val data by viewModel.tasks.collectAsState()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
    ) {

        Text(
            text = "Task",
            fontWeight = FontWeight(600),
            fontSize = 17.sp
        )
        OutlinedTextField(
            value = text,
            onValueChange = { newText -> text = newText },
            label = { Text("Nhập văn bản") }, // Nhãn cho trường nhập
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent ,
                unfocusedContainerColor = Color.Transparent
            ),
        )
        Spacer(
            modifier= Modifier.height(20.dp)
        )
        Text(
            text = "Description",
            fontWeight = FontWeight(600),
            fontSize = 17.sp
        )
        OutlinedTextField(
            value = description,
            onValueChange = { newText -> description = newText },
            label = { Text("Nhập văn bản") }, // Nhãn cho trường nhập
            modifier = Modifier.fillMaxWidth()
                .height(200.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent ,
                unfocusedContainerColor = Color.Transparent,
            ),
        )
        Spacer(
            modifier= Modifier.height(20.dp)
        )
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                onClick = {
                    val newTask = TaskData(title = text, description = description, id = randomID(),
                        attachments = emptyList(), category = "", createdAt = "", dueDate = "",
                        priority = "", reminders = emptyList(), status = "", subtasks = emptyList(), updatedAt = ""
                        )
                    viewModel.addTask(newTask)
                    text = "" // Reset input
                    description = "" // Reset input
                    println(viewModel.tasks.value)


                },
                colors = ButtonDefaults.buttonColors(containerColor = Blue, contentColor = Color.White),
                modifier = Modifier.width(100.dp)
            ) {
                Text("Add")
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopBarAddTask(){
    TopAppBar(
        title = {
            Text(
                text = "Add New",
                color = Blue,
                fontSize = 24.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            Card(
                colors = CardDefaults.cardColors(containerColor = Blue)
            ) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_back_ios_new_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },
        actions = {
             Spacer(modifier = Modifier.width(50.dp))
        }
    )
}

fun randomID(): Int {
    return UUID.randomUUID().hashCode() // Chuyển đổi UUID thành hash code
}