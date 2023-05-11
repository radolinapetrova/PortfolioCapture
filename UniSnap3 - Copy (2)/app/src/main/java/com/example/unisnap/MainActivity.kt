package com.example.unisnap

import BottomNavItem
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Feed
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Shop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.unisnap.ui.theme.UniSnapTheme

class MainActivity : ComponentActivity() {


    val context : Context = this

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UniSnapTheme() {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "Feed",
                                    route = "feed",
                                    icon = Icons.Default.Feed
                                ),
                                BottomNavItem(
                                    name = "My Archives",
                                    route = "archives",
                                    icon = Icons.Default.Archive
                                ),
                                BottomNavItem(
                                    name = "Leaderboard",
                                    route = "leaderboard",
                                    icon = Icons.Default.Leaderboard
                                ),

                                BottomNavItem(
                                    name = "Market",
                                    route = "market",
                                    icon = Icons.Default.Shop
                                ),
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) { padding->
                    Box(modifier = Modifier.padding(padding)){
                        Navigation(navController = navController, context)
                    }

                }
            }
        }
    }
}

@Composable
fun createPost(){
    CreatePostActivity()
}

@Composable
fun Navigation(navController: NavHostController, context: Context) {
    NavHost(navController = navController, startDestination = "feed") {
        composable("feed") {
           Nav()
        }
        composable("market") {
            MarketActivity(context = context, null, "", "")
        }
        composable("archives") {
            MyApp()
        }
        composable("leaderboard") {
            LeaderBoardActivity()
        }

    }
}


@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color(0xFFF9FEFF),
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color(0xFF985F6F),
                unselectedContentColor = Color.Black,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if(selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                        else{
                            Text(
                                text = item.name,
                                color = Color.Black,
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            )
        }
    }
}



