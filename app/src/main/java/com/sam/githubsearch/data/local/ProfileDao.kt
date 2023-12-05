package com.sam.githubsearch.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sam.githubsearch.data.model.ProfileData


@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfiles(articles: List<ProfileData>)

    @Query("SELECT * FROM profile")
    suspend fun getProfiles(): List<ProfileData>

    @Query("DELETE FROM profile")
    fun clear()
}