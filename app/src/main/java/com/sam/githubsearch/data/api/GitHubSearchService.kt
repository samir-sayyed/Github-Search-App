package com.sam.githubsearch.data.api

import com.sam.githubsearch.data.model.ProfileData
import com.sam.githubsearch.data.model.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubSearchService {

    @GET("search/repositories")
    suspend fun getProfiles(
        @Query("q") query: String = "language:swift",
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc"
    ): Response<ProfileResponse>

}