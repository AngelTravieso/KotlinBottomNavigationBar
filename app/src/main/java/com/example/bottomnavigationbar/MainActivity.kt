package com.example.bottomnavigationbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationbar.ui.theme.CustomBottomNavigationBarTheme

data class TabBarItem(
    val title: String,
    val icon: Int,
)

data class CollectBox(
    val title: String,
    val icon: Int,
)

data class PaymentBox(
    val description: String, val icon: Int
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
                ), TabBarItem(
                    title = "Pagos",
                    icon = R.drawable.paymeny,
                ), TabBarItem(
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
                    Image(painter = painterResource(id = R.drawable.background_cc),
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
                            })

                    // ChinChin Logo
                    Image(painterResource(id = R.drawable.chinchin_logo),
                        contentDescription = "Chinchin Logo",
                        modifier = Modifier
                            .size(140.dp)
                            .layout { measurable, constraints ->
                                val placeable = measurable.measure(constraints)
                                layout(placeable.width, placeable.height) {
                                    placeable.place(
                                        0, -100
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
                                top = 70.dp,  // Ajusta la distancia desde arriba para dejar espacio para el logotipo
                                bottom = 0.dp, start = 0.dp, end = 0.dp
                            )
                    ) {
                        // Content of the screens in the NavHost
                        NavHost(
                            navController = navController, startDestination = tabBarItems[0].title
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

                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .layout { measurable, constraints ->
                                val placeable = measurable.measure(constraints)
                                layout(placeable.width, placeable.height) {
                                    placeable.place(
                                        0, constraints.maxHeight - placeable.height
                                    ) // Fija el FloatingTabView en el fondo
                                }
                            }) {
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
fun FloatingTabView(
    tabBarItems: List<TabBarItem>,
    navController: NavHostController,
    floatingMargin: Dp = 16.dp,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    // Wrapping our NavigationBar in a Surface to apply elevation.
    Surface(
        modifier = Modifier.padding(all = floatingMargin),
        shape = MaterialTheme.shapes.extraLarge, // This provides rounded edges to the Surface
        shadowElevation = 2.dp // Adjust shadow elevation for the floating effect
    ) {
        NavigationBar(contentColor = Color(0xFF14C6A4)) {
            tabBarItems.forEachIndexed { index, item ->

                NavigationBarItem(label = {
                    Text(text = item.title)
                }, icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(20.dp),
                    )
                },

                    selected = index == selectedTabIndex, onClick = {
                        if (index != selectedTabIndex) {
                            onTabSelected(index)
                            navController.navigate(item.title) {
                                // This makes sure the back button does not navigate through the tab history.
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    }, colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                            LocalAbsoluteTonalElevation.current
                        ),
                        selectedTextColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}


@Composable
fun HomeViewUI() {
    Column() {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .shadow(elevation = 8.dp, shape = MaterialTheme.shapes.extraLarge)
        ) {
            CollectionBox()
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .shadow(elevation = 8.dp, shape = MaterialTheme.shapes.extraLarge)
        ) {
            PaymentBox()
        }
    }
}

@Composable
fun CollectionBox() {

    val collectBoxItems = listOf(
        CollectBox(
            title = "Identipago", icon = R.drawable.identipago
        ),
        CollectBox(
            title = "Chinchin", icon = R.drawable.chinchin
        ),
        CollectBox(
            title = "Pago Movil", icon = R.drawable.pago_movil
        ),
        CollectBox(
            title = "Bancos Nacionales", icon = R.drawable.bancos_nacionales
        ),
    )

    Column(modifier = Modifier.background(Color.White)) {
        Text(
            text = "Recaudar",
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 16.dp, start = 24.dp)
        )

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(collectBoxItems) { item ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                        .clip(CircleShape) // Aplica el clip de forma circular al box
                        .background(color = Color(0xFF2D2A26))
                        .aspectRatio(1f) // Asegura que el width y el height sean iguales para hacer el círculo
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = item.title,
                            tint = Color.White,
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp) // Agrego padding horizontal para controlar el ancho máximo
                        ) {
                            Text(
                                text = item.title,
                                color = Color.White,
                                fontWeight = FontWeight.W500,
                                textAlign = TextAlign.Center,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis // Esto garantiza que el texto se divida en dos líneas si es necesario
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PaymentBox() {

    val paymentBoxItems = listOf(
        PaymentBox(
            description = "Movistar", icon = R.drawable.movistar
        ),
        PaymentBox(
            description = "Movistar", icon = R.drawable.movistar
        ),
        PaymentBox(
            description = "SimpleTV", icon = R.drawable.simple_tv
        ),
        PaymentBox(
            description = "Digitel", icon = R.drawable.digitel
        ),
        PaymentBox(
            description = "Movistar", icon = R.drawable.movistar
        ),
    )

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(10.dp)
    ) {
        Text(
            text = "Pagos",
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 12.dp, start = 24.dp, bottom = 12.dp)
        )

        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            items(paymentBoxItems) { item ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 5.dp)
                        .border(1.2.dp, Color(0xFF2D2A26), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.description,
                        tint = Color(0xFF2D2A26),
                        modifier = Modifier
                            .padding(5.dp)
                            .width(80.dp)
                            .height(40.dp)
                    )
                }
            }

            item {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp) // Espacio adicional entre los elementos de la lista y el botón
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center, // Centra el contenido horizontalmente
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { /* Manejar clic del botón */ },
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Agregar",
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}