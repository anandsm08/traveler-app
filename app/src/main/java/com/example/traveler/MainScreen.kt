package com.example.traveler

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text("Traveler")
            },

            actions = {

                IconButton(onClick = { navController.navigate("profile") }) {
                    Icon(Icons.Filled.Person, contentDescription = "Profile")
                }
            }
        )
    }, bottomBar = {
        BottomBar(navController = navController)

    }) {it;
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController){
    val screens = listOf(
        NavItems.Capture,
        NavItems.Maps,
        NavItems.Saved,

    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

// ******
    Row(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 4.dp, bottom = 4.dp)
            .background(Color.White)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        screens.forEach{
                screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }

    }

}

@Composable
fun RowScope.AddItem(
    screen: NavItems,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route }== true


    val background =
        if (selected) Color.Gray.copy(alpha = 0.6f) else Color.Transparent

    val contentColor =
        if (selected) Color.White else Color.Black

    Box(
        modifier = Modifier
            .height(50.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {

        Row(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 12.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)

        ) {
            Icon(
                imageVector = ImageVector.vectorResource(screen.icon),
                contentDescription = "icon",
                tint = contentColor
            )
            AnimatedVisibility(visible = selected) {
                Text(
                    text = screen.title,
                    color = contentColor
                )
            }
        }
    }

}


@Composable
@Preview
fun MainScreenPreview(){
    val navController   = rememberNavController()
    MainScreen()
}

// *


//    BottomNavigation {
//        screens.forEach{
//            screen ->
//            AddItem(screen = screen, currentDestination = currentDestination, navController = navController )
//        }
//    }

//@Composable
//fun RowScope.AddItem(
//    screen: NavItems,
//    currentDestination: NavDestination?,
//    navController: NavHostController
//){
//    BottomNavigationItem(
//        alwaysShowLabel = false,
//        label = {
//            Text(text = screen.title)
//        },
//        icon = {
//            Icon(
//                imageVector = ImageVector.vectorResource(screen.icon),
//                contentDescription =  "Navigation Icons"
//            )
//        },
//        selected = currentDestination?.hierarchy?.any {
//          it.route == screen.route
//        }== true,
//        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
//        onClick = {
//            navController.navigate(screen.route){
//                popUpTo(navController.graph.findStartDestination().id)
//                launchSingleTop = true
//            }
//        },
//    )
//}