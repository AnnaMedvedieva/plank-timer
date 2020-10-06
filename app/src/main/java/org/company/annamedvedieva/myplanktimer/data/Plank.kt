package org.company.annamedvedieva.myplanktimer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plank_time_table")
data class Plank (
    @PrimaryKey(autoGenerate = true)
    var plankId: Long = 0L,

    @ColumnInfo(name = "plank_type")
    var plankType: String? = "Low plank",

    @ColumnInfo(name = "duration")
    var duration: String = "0",

    @ColumnInfo(name = "date")
    var date: String = "")

