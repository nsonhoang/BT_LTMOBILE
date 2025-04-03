package com.example.bai1_tuan4

sealed class Screens (val screen: String){
    data object Home: Screens("home")
    data object Search: Screens("search")
    data object Profile: Screens("profile")
    data object Notification: Screens("notification")

}