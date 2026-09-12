package com.example.farshbazar.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.farshbazar.data.model.*

@Database(
    entities = [
        Carpet::class,
        Vendor::class,
        Review::class,
        Suggestion::class,
        UserProfile::class
    ],
    version = 1,
    exportSchema = false
)
abstract class FarshBazarDatabase : RoomDatabase() {
    abstract fun farshBazarDao(): FarshBazarDao

    companion object {
        @Volatile
        private var INSTANCE: FarshBazarDatabase? = null

        fun getDatabase(context: Context): FarshBazarDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FarshBazarDatabase::class.java,
                    "farsh_bazar_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
