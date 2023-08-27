package dev.virunarala.trackhub.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.virunarala.trackhub.data.db.model.RepositoryEntity

@Dao
interface RepoDao {

    @Query("SELECT * FROM ${DbConstants.TABLE_REPOSITORY} ORDER BY ${DbConstants.COLUMN_REPO_NAME} COLLATE NOCASE")
    suspend fun getAllRepos(): List<RepositoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repo: RepositoryEntity): Long

}