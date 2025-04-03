package com.example.bai1_tuan4

import UserViewModel
import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.bai1_tuan4.ui.theme.Bai1_tuan4Theme
import com.example.bai1_tuan4.ui.theme.Blue
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Login : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var authManager: Auth.AuthenticationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        authManager = Auth.AuthenticationManager(this)

        setContent {
            Bai1_tuan4Theme{
            val navController = rememberNavController()
                if (auth.currentUser == null){
                    LoginScreen(auth,authManager, activity = this, viewModel = UserViewModel())
                }
                else{
                    UserInfo(auth, activity = this,viewModel = UserViewModel())
                }

            }
        }
    }
}

@Composable
fun LoginScreen(auth: FirebaseAuth,authManager: Auth.AuthenticationManager,activity: Activity,viewModel: UserViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                colors = CardDefaults.cardColors(Color.Transparent),
                modifier = Modifier.width(200.dp).height(200.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.uth_logo),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().padding(40.dp)
                )
            }
            Text(
                text = "SmartTasks",
                color = Blue,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "A simple and efficient to-do app", color = Blue)
            Spacer(modifier = Modifier.height(100.dp))
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Blue, contentColor = Color.White),
                onClick = {
                    coroutineScope.launch {
                        authManager.signInWithGoogle().collect { response ->
                            when (response) {
                                is AuthResponse.Success -> {
                                    Log.d("LoginScreen", "Đăng nhập thành công")
                                    withContext(Dispatchers.Main) {
                                        activity.finish() // Đảm bảo chạy trên UI Thread
                                    }
                                }
                                is AuthResponse.Error -> Log.e("LoginScreen", "Lỗi: ${response.message}")
                            }
                        }
                    }
                }
            ) {
                Image(
                    painter = painterResource(R.drawable.google),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
                Spacer(
                    modifier = Modifier.width(5.dp)
                )
                Text(
                    text = "Sign in with Google",
                    fontWeight = FontWeight(800),
                    fontSize = 20.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfo(auth: FirebaseAuth, activity: Activity,viewModel: UserViewModel){
    val context = LocalContext.current
    var text by remember { mutableStateOf("") }
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Profile",
                        fontSize = 24.sp,
                        fontWeight = FontWeight(600),
                        textAlign = TextAlign.Center,
                        color = Blue,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Blue)
                    ) {
                        IconButton(onClick = {

                        }) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_arrow_back_ios_new_24),
                                contentDescription = null, tint = Color.White)
                        }
                    }
                },
                actions = {
                    Spacer(modifier = Modifier.size(50.dp))
                }
            )
        }
    ){
        innerPadding->
        Column( Modifier.padding(innerPadding)
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxSize(),
             verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                ProfileImage()
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "Name",
                    fontWeight = FontWeight(600),
                    fontSize = 17.sp
                )
                OutlinedTextField(
                    value = auth.currentUser?.displayName.toString(),
                    onValueChange = { newText -> text = newText },
                    //label = { Text("Nhập văn bản") }, // Nhãn cho trường nhập
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(),
                    )
                Text(
                    text = "Email",
                    fontWeight = FontWeight(600),
                    fontSize = 17.sp
                )
                OutlinedTextField(
                    value = text,
                    onValueChange = { newText -> text = newText },
                    label = { Text("Nhập văn bản") }, // Nhãn cho trường nhập
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(),
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val result= auth.signOut()
                    if (auth.currentUser == null){
                        Toast.makeText(context,"Đăng xuất thành công",Toast.LENGTH_SHORT).show()
                        viewModel.setLoggedIn(false)
                        activity.finish()
                    }
                    else{
                        Toast.makeText(context,"Đăng xuất không thành công",Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Blue, contentColor = Color.White)
            ) { Text("Sign Out") }
        }
    }

}

@Preview
@Composable
fun ProfileImage(){
    val imageUrl = rememberSaveable { mutableStateOf("") }
    val painter = rememberImagePainter(
        if (imageUrl.value.isEmpty()){
            R.drawable.baseline_person_24
        } else {
            imageUrl.value
        }
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {uri:Uri?->
        uri?.let { imageUrl.value = it.toString() }
    }
    Column (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
        ) {
            Image(
                painter= painter,
                contentDescription = null,
                modifier = Modifier
                    .clickable { launcher.launch("image/*") },
                contentScale = ContentScale.Crop
            )
        }
    }
}
