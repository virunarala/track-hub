package dev.virunarala.trackhub.data.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDbInstance(@ApplicationContext context: Context): RepoDb {
        return Room.databaseBuilder(
            context,
            RepoDb::class.java,
            DbConstants.DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRepoDao(repoDb: RepoDb): RepoDao {
        return repoDb.repoDao()
    }
}