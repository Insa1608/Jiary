package com.example.jiary.base.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

/**
 * The different database queries are described in the Dao interface
 */

@Dao
interface JournalDao {

    //Upsert inserts a new entry or automatically overwrites an existing one
    @Upsert
    suspend fun upsertJournalEntity(journalEntity: JournalEntity)

    @Delete
    suspend fun deleteJournalEntity(journalEntity: JournalEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateJournalEntity(journalEntity: JournalEntity)

    @Query("SELECT * FROM journalentity")
    suspend fun getAllJournalEntities(): List<JournalEntity>


}