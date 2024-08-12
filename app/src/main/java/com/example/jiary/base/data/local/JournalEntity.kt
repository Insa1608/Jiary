package com.example.jiary.base.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Entity is generated here for the different fields which should be saved in the database
 * The Primary Key is generated automatically. The columns of the database entries are described
 * with the ColumnInfo annotation
 */
@Entity
data class JournalEntity(
    @ColumnInfo(name = "journal_title")
    var title: String,
    @ColumnInfo(name = "journal_entry")
    var entry: String,
    @ColumnInfo(name = "journal_date")
    var date: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
)