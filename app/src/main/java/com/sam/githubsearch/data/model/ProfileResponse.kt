package com.sam.githubsearch.data.model

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,
    val items: List<ProfileData>? = null,
    @SerializedName("total_count")
    val totalCount: Int? = null
)