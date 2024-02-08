package com.example.bottomnavigationbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationbar.ui.theme.CustomBottomNavigationBarTheme

data class TabBarItem(

    val title: String,
    val icon: Int,
)

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val tabBarItems = listOf(
                TabBarItem(
                    title = "Inicio",
                    icon = R.drawable.home,
                ),
                TabBarItem(
                    title = "Pagos",
                    icon = R.drawable.paymeny,
                ),
                TabBarItem(
                    title = "Operaciones",
                    icon = R.drawable.operations,
                )
            )

            // creating our navController
            val navController = rememberNavController()
            var selectedItem by remember { mutableStateOf(0) }

            CustomBottomNavigationBarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color= Color(0xFF14C6A4),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Scaffold { innerPadding ->
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(
                                    bottom = 0.dp,
                                    start = 0.dp,
                                    end = 0.dp
                                )
                                .padding(top = innerPadding.calculateTopPadding())
                        ) {
                            // Content of the screens in the NavHost
                            NavHost(
                                navController = navController,
                                startDestination = tabBarItems[0].title
                            ) {
                                composable(tabBarItems[0].title) {
                                    Text(tabBarItems[0].title)
                                }
                                composable(tabBarItems[1].title) {
                                    Text(tabBarItems[1].title)
                                }
                                composable(tabBarItems[2].title) {
                                    Text(tabBarItems[2].title)
                                }
                            }

                            Column(
                                modifier = Modifier.fillMaxHeight(),
                                verticalArrangement = Arrangement.Bottom,
                            ) {
                                FloatingTabView(
                                    tabBarItems = tabBarItems,
                                    navController = navController,
                                    floatingMargin = 10.dp,
                                    selectedTabIndex = selectedItem
                                ) { index ->
                                    selectedItem = index
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatingTabView(
    tabBarItems: List<TabBarItem>,
    navController: NavHostController,
    floatingMargin: Dp = 16.dp,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    // Wrapping our NavigationBar in a Surface to apply elevation.
    Surface(
        modifier = Modifier
            .padding(all = floatingMargin),
        shape = MaterialTheme.shapes.extraLarge, // This provides rounded edges to the Surface
       shadowElevation = 2.dp // Adjust shadow elevation for the floating effect
    ) {
        NavigationBar(contentColor = Color(0xFF14C6A4)) {
            tabBarItems.forEachIndexed { index, item ->

                NavigationBarItem(
                    icon = {
                        Row {
                            Icon(
                                painterResource(id = item.icon),
                                contentDescription = item.title,
                                modifier = Modifier.size(24.dp),
                            )

                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = item.title)
                        }
                    },

                    selected = index == selectedTabIndex,
                    onClick = {
                        if (index != selectedTabIndex) {
                            onTabSelected(index)
                            navController.navigate(item.title) {
                                // This makes sure the back button does not navigate through the tab history.
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    },
                    colors = androidx.compose.material3.NavigationBarItemDefaults
                        .colors(
                            selectedIconColor = Color(0xFF14C6A4),
                            indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(LocalAbsoluteTonalElevation.current)
                        )
                )
            }
        }
    }
}
