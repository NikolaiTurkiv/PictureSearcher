package com.test.core_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.core_db.dao.RequestDao
import com.test.core_db.entities.RequestInfoEntity

@Database(entities = [RequestInfoEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun requestInfoDao():RequestDao
}