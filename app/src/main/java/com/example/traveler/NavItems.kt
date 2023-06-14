package com.example.traveler

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItems(
        val icon : Int,
        val title: String,
        val route: String

){
        object Capture: NavItems(R.drawable.baseline_photo_camera_24, "Snap", "capture")
        object Maps: NavItems(R.drawable.baseline_map_24, "Maps", "maps")
        object Saved: NavItems(R.drawable.baseline_bookmarks_24, "Bookmarks", "saved")

}
