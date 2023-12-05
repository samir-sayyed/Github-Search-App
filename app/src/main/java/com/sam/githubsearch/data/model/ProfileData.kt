package com.sam.githubsearch.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "profile")
data class ProfileData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String? = null,
    val owner: Owner? = null,
) {
    data class Owner(
        @SerializedName("avatar_url")
        val avatarUrl: String? = null,
        @SerializedName("html_url")
        val repoUrl: String? = null
    )
}
