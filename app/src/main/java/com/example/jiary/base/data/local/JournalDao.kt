package com.example.jiary.base.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

/**
 * The different database queries are described in the Dao interface
 */

@Dao
interface JournalDao {

    //Upsert inserts a new entry or automatically overwrites an existing one
    @Upsert
    suspend fun upsertJournalEntity(journalEntity: JournalEntity)
    //Delets a journal entry
    @Delete
    suspend fun deleteJournalEntity(journalEntity: JournalEntity)
    //Queries a journal entry by a specific id
    @Query("SELECT * FROM journalentity WHERE id = :id")
    suspend fun getJournalEntityById(id: Int): JournalEntity?
    //queries all available journal entries
    @Query("SELECT * FROM journalentity")
    suspend fun getAllJournalEntities(): List<JournalEntity>

}