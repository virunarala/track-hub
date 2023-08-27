package dev.virunarala.trackhub.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.virunarala.trackhub.data.db.model.RepositoryEntity
import dev.virunarala.trackhub.data.repository.GithubRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val githubRepo: GithubRepo) : ViewModel() {

    var reposFlow = MutableStateFlow<List<RepositoryEntity>?>(null)

    fun getRepos() {
        viewModelScope.launch {
            reposFlow.value = githubRepo.getAllRepos()
        }
    }
}