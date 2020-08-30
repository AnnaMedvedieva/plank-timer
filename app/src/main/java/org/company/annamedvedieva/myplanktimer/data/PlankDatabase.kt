package org.company.annamedvedieva.myplanktimer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Plank::class], version = 1, exportSchema = false)
abstract class PlankDatabase: RoomDatabase() {

    abstract val plankDao: PlankDao

    companion object {

        @Volatile
        private var INSTANCE: PlankDatabase? = null

        fun getInstance(context: Context): PlankDatabase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PlankDatabase::class.java,
                        "plank_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

