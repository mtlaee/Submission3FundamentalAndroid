package com.example.submission2aplikasigithubtaufikakbar.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteUser::class],
    version = 1
)
abstract class DbUser: RoomDatabase() {
    companion object {
        var INSTANCE : DbUser? =null

        fun getDB(context: Context) : DbUser? {
            if (INSTANCE==null) {
                synchronized(DbUser::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, DbUser::class.java, "Db_User").build()
                }
            }
            return INSTANCE
        }
    }
    abstract fun favoriteUserDao(): FavoriteUserDAO
}
