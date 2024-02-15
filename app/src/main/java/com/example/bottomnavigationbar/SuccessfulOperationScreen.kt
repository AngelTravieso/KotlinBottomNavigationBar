package com.example.bottomnavigationbar

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.bottomnavigationbar.utils.getCurrentDateTimeFormatted

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SucessfulOperationScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Volver",
                        modifier = Modifier.size(40.dp)
                    )
                }
            },
            title = {
                Text(
                    text = "PAGOS",
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menú de hamburguesa"
                    )
                }
            }
        )
    }) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                .shadow(elevation = 2.dp, shape = MaterialTheme.shapes.extraSmall)
                .clip(RoundedCornerShape(30.dp))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp), // Agrega padding adentro de la Column si es necesario
            ) {

                Image(
                    painterResource(id = R.drawable.chinchin_logo),
                    contentDescription = "Logo Chinchin",
                    modifier = Modifier
                        .width(160.dp)
                        .padding(top = 10.dp, bottom = 16.dp)
                )

                Text(
                    text = "¡Operación exitosa!",
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.W500
                )
                Text(
                    text = getCurrentDateTimeFormatted(),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Text(
                        text = "Total Debitado",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Text(text = "0,02 BS", fontWeight = FontWeight.W400, fontSize = 16.sp)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Text(
                        text = "Total Acreditado",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Text(text = "0,02 BS", fontWeight = FontWeight.W400, fontSize = 16.sp)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Text(text = "Estatus", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                    Text(text = "Confirmada", fontWeight = FontWeight.W400, fontSize = 16.sp)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Text(text = "#Chinchin", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                    Text(text = "as87d68a7s6dasd", fontWeight = FontWeight.W400, fontSize = 16.sp)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Text(text = "Descripción", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                    Text(
                        text = "transaction chinchin",
                        fontWeight = FontWeight.W400,
                        fontSize = 18.sp
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                ) {
                    TextButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.printer),
                            contentDescription = "Imprimir",
                            modifier = Modifier.size(45.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Inicio", fontSize = 18.sp, color = Color.Black)
                    }


                    TextButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.printer),
                            contentDescription = "Imprimir",
                            modifier = Modifier.size(45.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Imprimir", fontSize = 18.sp, color = Color.Black)
                    }
                }
            }

        }
    }
}