package com.example.headphones.navigation

sealed class Screens(val route: String) {
    object HeadphonesListScreen: Screens("main_page")
    object MyAdvertisements: Screens("myAdvertisements_page")
    object UpdateItemScreen: Screens("update_page")
    object AddNewScreen: Screens("addNew_page")

}