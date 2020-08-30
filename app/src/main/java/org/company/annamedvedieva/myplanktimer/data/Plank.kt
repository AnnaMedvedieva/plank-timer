package org.company.annamedvedieva.myplanktimer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "plank_time_table")
data class Plank (

    @PrimaryKey(autoGenerate = true)
    var plankId: Long = 0L,

    @ColumnInfo(name = "plank_type")
    var plankType: String = "",

    @ColumnInfo(name = "duration")
    var duration: Long = 0L,

    @ColumnInfo(name = "date")
    var date: String = ""
)