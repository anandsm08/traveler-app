package com.example.traveler

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.traveler.screens.MapScreen
import com.example.traveler.screens.captureScreen
import com.example.traveler.screens.profileScreen
import com.example.traveler.screens.savedScreen

@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = NavItems.Maps.route
    ){
        composable( route = NavItems.Capture.route ){
            captureScreen()
        }
        composable( route = NavItems.Maps.route ){
            MapScreen()
        }
        composable( route = NavItems.Saved.route ){
            savedScreen()
        }
        composable( "profile"){
            profileScreen()
        }
    }
}