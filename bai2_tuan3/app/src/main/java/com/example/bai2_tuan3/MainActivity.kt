package com.example.bai2_tuan3


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.bai2_tuan3.ui.theme.Bai2_tuan3Theme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        var keepSplashScrenn = true
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition{keepSplashScrenn}
        lifecycleScope.launch {
            delay(5000)
            keepSplashScrenn = false
        }
        enableEdgeToEdge()
        setContent {
            Bai2_tuan3Theme {
                MainScreen()

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Preview
@Composable
fun MainScreen() {
    val pagerState = rememberPagerState()
    val items = createList()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(16.dp), activeColor = Color(0xFF006CD8)
            )
            HorizontalPager(
                count = items.size,
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                ) {
                    PagerHorizontal(items[page],
                        onNext = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage((page + 1) % items.size)
                            }
                        },
                        onPrevious = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage((page - 1) % items.size)
                            }
                        }

                    )
                }
            }
        }
    }


}

fun createList(): MutableList<Item> {
    val list = mutableListOf<Item>()
    list.add(
        Item(
            "Easy Time Management",
            "With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first",
            R.drawable.firstimage,
            false
        )
    )
    list.add(
        Item(
            "Increase Work Effectiveness",
            "Time management and the determination of more important tasks will give your job statistics better and always improve",
            R.drawable.secordimg,
            true
        )
    )
    list.add(
        Item(
            "Reminder Notification",
            "The advantage of this application is that it atso provides reminders for you so you don't forget to keep doing your assignments will and according to the time you have set",
            R.drawable.ththirdimg,
            true
        )
    )
    return list
}

data class Item(
    val title: String,
    val detail: String,
    val img: Int,
    val isVisible: Boolean,
)


@Composable
fun PagerHorizontal(data: Item, onNext: () -> Unit, onPrevious: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(300.dp))
            Image(
                painter = painterResource(data.img),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
            )
            Text(
                text = data.title,
                fontSize = 24.sp,
                fontWeight = FontWeight(600)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = data.detail,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(300.dp)
            )
        }
        Row() {
            if (data.isVisible) {
                IconButton(
                    onClick = {
                        onPrevious()

                    },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(999.dp))
                        .background(Color(0xFF006CD8))
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_back_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
            }
            Button(
                onClick = {
                    onNext()

                },
                colors = ButtonColors(
                    Color(0xFF006CD8),
                    Color.White,
                    Color(0xFF006CD8),
                    Color.White
                ),
            ) {
                Text(
                    text = "Next",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

            }
        }

    }

}
