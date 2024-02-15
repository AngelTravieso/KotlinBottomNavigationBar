package com.example.bottomnavigationbar.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentDateTimeFormatted(): String {
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("dd, MMMM, yyyy hh:mm a", Locale.ENGLISH)
    return currentDateTime.format(formatter)
}