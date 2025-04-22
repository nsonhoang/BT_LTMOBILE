package com.example.bai1_tuan3

import android.content.Intent
import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bai1_tuan3.ui.theme.Bai1_tuan3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeApp()
        }
    }

    @Preview
    @Composable
    fun ComposeApp() {
        val navController = rememberNavController()
        Bai1_tuan3Theme {

            NavHost(navController = navController, startDestination = "home") {
                composable("home"){
                    ComposeWithImgAndText(modifier = Modifier.fillMaxSize(), openUiComponent = {
                        navController.navigate("UiComponentList")
                    })
                }
                composable("UiComponentList"){
                    DisplayUiComponents(onTextDetail = {title,detail->
                        navController.navigate("TextDetail/$title/$detail")
                    },
                        onHome = {
                            navController.navigate( "home"){
                                popUpTo(0)
                            }
                        }
                        )
                }
                composable(route = "TextDetail/{title}/{detail}",
                    arguments = listOf(
                        navArgument(name="title"){
                            type =NavType.StringType
                        },
                        navArgument(name = "detail"){
                            type = NavType.StringType
                        }
                    )
                    ){it ->
                    val title =it.arguments?.getString("title")
                    requireNotNull(title)
                    val detail = it.arguments?.getString("detail")
                    requireNotNull(detail)
                   TextDetailDisplay(title = title, detail = detail, goHome = {
                       navController.navigate("home")
                   })
                }
            }
        }


    }

    @Composable
    fun ComposeWithImgAndText(modifier: Modifier = Modifier,openUiComponent : ()-> Unit) {
        val context = LocalContext.current
        Column {
            Column(
                modifier = modifier.wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(R.drawable.image1),
                    contentDescription = "imgage",
                    modifier = Modifier
                        .width(216.dp)
                        .height(233.dp)
                )
                Spacer(modifier = Modifier.height(40.dp))
                Column(
                    modifier = Modifier
                        .width(301.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(
                        text = "Jetpack Compose",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight(500)
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400)

                    )
                }
                Spacer(modifier = Modifier.height(300.dp))
                Button(
                    onClick = {
                         openUiComponent()
                    },
                    modifier = Modifier.width(301.dp),

                    ) {
                    Text(text = "Get ready",)
                }
            }
        }
    }
}