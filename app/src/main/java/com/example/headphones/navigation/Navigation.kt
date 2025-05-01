package com.example.headphones.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.headphones.addNew.Add
import com.example.headphones.appvertisements.MyAdvertisements
import com.example.headphones.delete.DeleteItem
import com.example.headphones.detailedPage.DetailedItem
import com.example.headphones.editPage.UpdateItemCard
import com.example.headphones.homePage.HomePage

@Composable
fun Navigation(navController: NavHostController, context: android.content.Context){
    NavHost(navController = navController, startDestination = Screens.HeadphonesListScreen.route){
        composable(Screens.HeadphonesListScreen.route){
            HomePage(
                onCardClick = {headphonesId ->
                    navController.navigate("detailedView/$headphonesId")
                },
                advertisementsDrawerClick = {
                    navController.navigate(Screens.MyAdvertisements.route)
                }
            )
        }
        composable(Screens.AddNewScreen.route){
            Add(
                onBackArrowClick = {
                    navController.navigate(Screens.MyAdvertisements.route)
                },
            )
        }
        composable(Screens.MyAdvertisements.route){
            MyAdvertisements(
                onBackArrowClick = {
                navController.navigate(Screens.HeadphonesListScreen.route)
                },
                onAddClick = {
                    navController.navigate(Screens.AddNewScreen.route)
                },
                onEditClick = {headphoneId ->
                    navController.navigate("update/$headphoneId")
                },
                onDeleteClick = {headphoneId ->
                    navController.navigate("delete/$headphoneId")
                }
            )
        }

        composable(
            route = "detailedView/{headphoneId}"
        ) { backStackEntry ->
            DetailedItem(headphonesId = backStackEntry.arguments?.getString("headphoneId")!!) {
                navController.navigate(Screens.HeadphonesListScreen.route)
            }
        }

        composable(
            route = "update/{headphoneId}"
        ) { backStackEntry ->
            UpdateItemCard(headphonesId = backStackEntry.arguments?.getString("headphoneId")!!)
        }
        composable(
            route = "delete/{headphoneId}"
        ) { backStackEntry ->
            DeleteItem(headphonesId = backStackEntry.arguments?.getString("headphoneId")!!)
            navController.navigate(Screens.MyAdvertisements.route)
        }
    }
}