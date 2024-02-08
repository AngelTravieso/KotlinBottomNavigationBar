package com.example.bottomnavigationbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
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

            val navController = rememberNavController()
            var selectedItem by remember { mutableIntStateOf(0) }

            CustomBottomNavigationBarTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Fondo
                    Image(
                        painter = painterResource(id = R.drawable.background_cc),
                        contentDescription = "Imagen fondo Chinchin",
                        modifier = Modifier
                            .fillMaxSize()
                            .layout { measurable, constraints ->
                                val placeable = measurable.measure(constraints)
                                layout(placeable.width, placeable.height) {
                                    placeable.place(
                                        40, // Posición a la derecha de la pantalla
                                        0 // Sin desplazamiento vertical
                                    )
                                }
                            }
                    )

                    // ChinChin Logo
                    Image(
                        painterResource(id = R.drawable.chinchin_logo),
                        contentDescription = "Chinchin Logo",
                        modifier = Modifier
                            .size(160.dp)
                            .layout { measurable, constraints ->
                                val placeable = measurable.measure(constraints)
                                layout(placeable.width, placeable.height) {
                                    placeable.place(
                                        0,
                                        -100
                                    ) // Posiciona la imagen en la esquina superior izquierda
                                }
                            }
                            .padding(16.dp) // Añadir espacio desde el borde
                    )

                    // Contenido principal
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = 150.dp,  // Ajusta la distancia desde arriba para dejar espacio para el logotipo
                                bottom = 0.dp,
                                start = 0.dp,
                                end = 0.dp
                            )
                    ) {
                        // Content of the screens in the NavHost
                        NavHost(
                            navController = navController,
                            startDestination = tabBarItems[0].title
                        ) {
                            composable(tabBarItems[0].title) {
                                HomeViewUI()
                            }
                            composable(tabBarItems[1].title) {
                                Text(tabBarItems[1].title)
                            }
                            composable(tabBarItems[2].title) {
                                Text(tabBarItems[2].title)
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .layout { measurable, constraints ->
                                    val placeable = measurable.measure(constraints)
                                    layout(placeable.width, placeable.height) {
                                        placeable.place(
                                            0,
                                            constraints.maxHeight - placeable.height
                                        ) // Fija el FloatingTabView en el fondo
                                    }
                                }
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

@Composable
fun DrawerContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Opción 1")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Opción 2")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Opción 3")
        // Puedes agregar más elementos de menú según sea necesario
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
                                modifier = Modifier.size(20.dp),
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
                            indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                LocalAbsoluteTonalElevation.current
                            )
                        )
                )
            }
        }
    }
}


@Composable
fun HomeViewUI() {
    Text(text = "Home")
}