package com.sam.githubsearch.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sam.githubsearch.data.model.ProfileData

@Database(entities = [ProfileData::class], version = 1)
@TypeConverters(ProfileTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getProfileDao(): ProfileDao
}