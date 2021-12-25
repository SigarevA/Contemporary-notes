package ru.bsc.contemporaryNotes.data.bd.converters

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun fromDate(value: Date): Long = value.time

    @TypeConverter
    fun toDate(value: Long): Date = Date(value)
}