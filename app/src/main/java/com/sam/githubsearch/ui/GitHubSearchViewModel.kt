package com.sam.githubsearch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sam.githubsearch.data.GitHubSearchRepository
import com.sam.githubsearch.data.model.ProfileData
import com.sam.githubsearch.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitHubSearchViewModel @Inject constructor(
    private val repository: GitHubSearchRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _profiles = MutableLiveData<List<ProfileData>?>()
    val profiles: LiveData<List<ProfileData>?> = _profiles

    fun getProfiles() = viewModelScope.launch(ioDispatcher) {
        repository.getProfiles().collectLatest {
          _profiles.postValue(it)
        }
    }

}