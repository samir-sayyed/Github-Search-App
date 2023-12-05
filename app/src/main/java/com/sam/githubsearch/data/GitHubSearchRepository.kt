package com.sam.githubsearch.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.sam.githubsearch.base.BaseApiRepository
import com.sam.githubsearch.data.api.GitHubSearchService
import com.sam.githubsearch.data.local.ProfileDao
import com.sam.githubsearch.data.model.ProfileData
import com.sam.githubsearch.util.ApiResult
import com.sam.movieapp.util.NetworkStatusHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GitHubSearchRepository @Inject constructor(
    private val gitHubSearchService: GitHubSearchService,
    private val profileDao: ProfileDao,
    @ApplicationContext private val context: Context
) : BaseApiRepository(context) {

    /*
    * Get profiles from local database
    * If network is available, get profiles from API and update local database
     */
    fun getProfiles() = flow {
        try {
            val cachedProfiles = profileDao.getProfiles()
            if (NetworkStatusHelper.isOnline(context)) {
                val response = safeApiCall {
                    gitHubSearchService.getProfiles()
                }
                when (response) {
                    is ApiResult.Success -> response.let {
                        profileDao.clear()
                        profileDao.insertProfiles(it.data?.items ?: emptyList())
                        emit(it.data?.items)
                    }
                    is ApiResult.Error -> emit(cachedProfiles)
                    else -> Log.i("getProfiles", "getProfiles: Loading")
                }
            } else {
                emit(cachedProfiles)
            }
        } catch (e: Exception) {
            Log.e("getProfiles", "getProfiles: ${e.message}")
        }
    }

}