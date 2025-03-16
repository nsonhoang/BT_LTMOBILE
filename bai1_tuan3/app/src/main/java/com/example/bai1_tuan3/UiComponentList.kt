package com.example.bai1_tuan3

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
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bai1_tuan3.ui.theme.Bai1_tuan3Theme



class UiComponentList : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Bai1_tuan3Theme {
                DislayUiComponents()
            }
        }
    }
}
@Preview
@Composable
fun DislayUiComponents(){
    Scaffold(topBar = {

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
            ) {
            Text(text = "UI Components List", color = Color(0xFF2196F3), fontSize = 26.sp,
                fontWeight = FontWeight(600)
            )
        }

    }) {innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(20.dp)) {
        ListComponents("Display","Text","Image","Displays text","Display an image", modifier = Modifier)
        Spacer(modifier = Modifier
            .height(30.dp))
        ListComponents("Input","TextField","PasswordField","Input field for text","Input field for passwords", modifier = Modifier)
        Spacer(modifier = Modifier
            .height(30.dp))
        ListComponents("Layout","Column","Row","Arranges elements vertically","Arranges elements horizontally", modifier = Modifier)

    }}

}



@Composable
fun ListComponents(label: String,title1: String,title2: String,detail :String,detail2: String,modifier: Modifier){
    Column(
    ) {
        Text(text = label,
            fontWeight = FontWeight(600),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Component(title1,detail,modifier=Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(5.dp))
        Component(title2,detail2,modifier=Modifier.fillMaxWidth())

    }
}
@Composable
fun Component(title: String,detail :String,modifier: Modifier){
    val context = LocalContext.current
    Card(
        modifier=Modifier.fillMaxWidth()
            .clickable {

                val i =Intent(context,TextDetail::class.java)
                i.putExtra("title",title)
                i.putExtra("detail",detail)
                context.startActivity(i)
            }

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