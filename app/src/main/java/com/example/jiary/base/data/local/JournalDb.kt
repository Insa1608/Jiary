package com.example.jiary.base.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [JournalEntity::class],
    version = 2
)
abstract class JournalDb: RoomDatabase() {
    abstract val journalDao: JournalDao
}