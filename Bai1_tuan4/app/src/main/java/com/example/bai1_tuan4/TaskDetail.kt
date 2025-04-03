package com.example.bai1_tuan4

import Attachment
import Reminder
import Subtask
import TaskData
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bai1_tuan4.ui.theme.Bai1_tuan4Theme
import com.example.bai1_tuan4.ui.theme.Blue
import com.example.bai1_tuan4.ui.theme.LightBlue
import com.example.bai1_tuan4.ui.theme.LightGreen
import com.example.bai1_tuan4.ui.theme.LightRed


class TaskDetail : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val id = intent.getIntExtra("id",0)
            Bai1_tuan4Theme {
                TaskDetailMain(id)
            }

        }
    }
}


@Composable
fun TaskDetailMain(id : Int){
    val viewModel: MainViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.fetchTaskData(id)
    }

    val data = viewModel.datatask.collectAsState().value

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
        Scaffold(
            topBar = {
                TopBar()

            }
        ) { innerPadding->
            MainScreen(data,Modifier.padding(innerPadding))


        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    val context = LocalContext.current
    TopAppBar(
        title = {
            Text(
                text = "Detail",
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
                IconButton(onClick = {
                    (context as? Activity)?.finish()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_back_ios_new_24),
                        contentDescription = null, tint = Color.White)
                }
            }
        },
        actions = {
            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(Color(0xFFF66A32))
            ) {
                IconButton(onClick = {

                }) {
                    Icon(Icons.Default.Delete, contentDescription = null, tint = Color.White)
                }
            }
        }
    )
}

@Composable
fun MainScreen(item : TaskData?,modifier: Modifier = Modifier){
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = item?.title ?: "",
            fontSize = 23.sp,
            fontWeight = FontWeight(600),
        )
        Text(
            text = item?.description ?: "",
        )
        Spacer(modifier = Modifier.height(20.dp))
        Status(item)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Subtasks",
            fontWeight = FontWeight(700),
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            if (item?.subtasks.isNullOrEmpty()) {
                item { NoSubtasks() }
            } else {
                // Nếu subtasks không rỗng, hiển thị danh sách các SubtaskItem
                items(item?.subtasks ?: emptyList()) { subtask ->
                    SubtaskItem(subtask)
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Attachments",
            fontWeight = FontWeight(700),
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            if (item?.attachments.isNullOrEmpty()) {
                item { NoAttachments() }
            } else {
                // Nếu subtasks không rỗng, hiển thị danh sách các SubtaskItem
                items(item?.attachments ?: emptyList()) { attachment ->
                    AttachmentItem(attachment)
                }
            }
        }
    }
}
@Composable
fun NoSubtasks(){
    Text(
        "No Subtask",
        fontSize = 12.sp,
        fontWeight = FontWeight(700),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
        )
}
@Composable
fun NoAttachments(){
    Text(
        "No attachments",
        fontSize = 12.sp,
        fontWeight = FontWeight(700),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun Status(item: TaskData?){
    val colorItem : Color
    when (item?.priority){
        "High"->{colorItem= LightRed}
        "Medium"->{colorItem= LightBlue
        }
        else ->{colorItem= LightGreen
        }
    }
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = colorItem)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {
           Row(
               verticalAlignment = Alignment.CenterVertically
           ) {
               Icon(
                   painter = painterResource(R.drawable.baseline_window_24),
                   contentDescription = null,
                   modifier = Modifier.size(30.dp)
               )
               Spacer(modifier = Modifier.width(5.dp))
               Column {
                   Text(text = "Category")
                   Spacer(modifier = Modifier.height(10.dp))
                   Text(
                       text = item?.category ?: "Unknown",
                       fontWeight = FontWeight(700)

                   )
               }
           }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_checklist_24),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Column{
                    Text(text = "Status")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = item?.status ?: "Unknown",
                        fontWeight = FontWeight(700)

                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_reward),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Column {
                    Text(text = "Priority")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = item?.priority ?: "Unknown",
                        fontWeight = FontWeight(700)

                    )
                }
            }
        }
    }
}


@Composable
fun SubtaskItem(item: Subtask){

    Card() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                onCheckedChange = {},
                checked = item.isCompleted
            )
            Text(text = item.title)
        }
    }
    Spacer(Modifier.height(5.dp))
}

@Composable
fun AttachmentItem(item: Attachment){
    Card() {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = item.fileName
            )
        }
    }
    Spacer(Modifier.height(10.dp))
}


@Composable
fun ReminderItem(item: Reminder){
    Card() {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = item.type
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = item.time
            )
        }
    }

}

