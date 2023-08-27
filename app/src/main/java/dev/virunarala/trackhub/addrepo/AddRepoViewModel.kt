package dev.virunarala.trackhub.addrepo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.virunarala.trackhub.data.network.model.NetworkResult
import dev.virunarala.trackhub.data.repository.GithubRepo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRepoViewModel @Inject constructor(private val githubRepo: GithubRepo) : ViewModel() {

    val addedRepo = MutableSharedFlow<NetworkResult>()

    fun getRepo(owner: String, repoName: String) {
        viewModelScope.launch {
            addedRepo.emit(NetworkResult.Loading)
            val result = githubRepo.getRepo(owner,repoName)
            addedRepo.emit(result)
        }
    }
}