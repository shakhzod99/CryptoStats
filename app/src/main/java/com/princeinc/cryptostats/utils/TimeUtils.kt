package com.princeinc.cryptostats.utils

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

 /*
    Перевод раницы времени с 1970года, которые берутся из сети -- в реальное время
 */
fun convertTimeStampToTime(timestamp: Long?): String{
     if(timestamp == null) return ""
    /*
    получили секунды прошедшего с 1970 года и умножили на 1000, получив миллисекунды
    */
    val stamp = Timestamp(timestamp* 1000)

    /* Получаем реальное время */
    val date = Date(stamp.time)

    /* Создаём паттерн, в соответствии которого получаем время */
    val patternTime = "HH:mm:ss" // паттерн: часы (24-часовой), минуты, секунды
    val simpleDateFormat = SimpleDateFormat(patternTime, Locale.getDefault())
    simpleDateFormat.timeZone = TimeZone.getDefault() // Опрелеляем время по нашей временной зоне
    return simpleDateFormat.format(date)
}