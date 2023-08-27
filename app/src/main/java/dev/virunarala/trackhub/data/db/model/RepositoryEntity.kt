package dev.virunarala.trackhub.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.virunarala.trackhub.data.db.DbConstants

@Entity(tableName = DbConstants.TABLE_REPOSITORY)
data class RepositoryEntity(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "html_url")
    val htmlUrl: String,

    @ColumnInfo(name = "description")
    val description: String?
)
