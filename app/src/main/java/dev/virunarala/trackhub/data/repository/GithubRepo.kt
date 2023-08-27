package dev.virunarala.trackhub.data.repository

import dev.virunarala.trackhub.data.db.RepoDao
import dev.virunarala.trackhub.data.db.model.RepositoryEntity
import dev.virunarala.trackhub.data.network.ApiEndpoint
import dev.virunarala.trackhub.data.network.model.NetworkResult
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.Exception

class GithubRepo @Inject constructor(private val apiEndpoint: ApiEndpoint, private val repoDao: RepoDao) {

    suspend fun getRepo(owner: String, repoName: String): NetworkResult {
        try {
            val repo = apiEndpoint.getRepo(owner, repoName)
            val rowId = addRepoToDb(repo.toRepositoryEntity())
            return NetworkResult.Success(repo)
        } catch (e: UnknownHostException) {
            return NetworkResult.NoInternet
        } catch (e: HttpException) {
            if(e.code()==404) {
                return NetworkResult.Error("Repo Not Found")
            } else {
                return NetworkResult.Error(e.message)
            }
        } catch (e: Exception) {
            return NetworkResult.Error(e.message)
        }
    }

    private suspend fun addRepoToDb(repositoryEntity: RepositoryEntity): Long {
        return repoDao.insert(repositoryEntity)
    }

    suspend fun getAllRepos(): List<RepositoryEntity>? {
        try {
            return repoDao.getAllRepos()
        } catch (e: Exception) {
            return null
        }
    }
}