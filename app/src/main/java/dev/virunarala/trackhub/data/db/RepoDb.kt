package dev.virunarala.trackhub.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.virunarala.trackhub.data.db.model.RepositoryEntity

@Database(entities = [RepositoryEntity::class], version = 1)
abstract class RepoDb: RoomDatabase() {
    abstract fun repoDao(): RepoDao
}