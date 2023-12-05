package com.sam.githubsearch.data.local


import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sam.githubsearch.data.model.ProfileData

@TypeConverters
class ProfileTypeConverter {

    @TypeConverter
    fun fromOwner(owner: ProfileData.Owner?): String? {
        if (owner == null) return null
        return Gson().toJson(owner)
    }

    @TypeConverter
    fun toOwner(source: String?) : ProfileData.Owner?{
        if (source == null) return null
        return Gson().fromJson(source, object : TypeToken<ProfileData.Owner>() {}.type)
    }

}