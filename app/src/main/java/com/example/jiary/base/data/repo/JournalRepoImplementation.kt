package com.example.jiary.base.data.repo


import com.example.jiary.base.data.Mapper.toJournalEntity
import com.example.jiary.base.data.Mapper.toJournalItem
import com.example.jiary.base.data.local.JournalDb
import com.example.jiary.base.domain.model.JournalItem
import com.example.jiary.base.domain.model.repo.JournalRepo

/**
 * Acts like an interface between the data layer and the business layer
 */

class JournalRepoImplementation(
    journalDb: JournalDb
): JournalRepo{

    private val journalDao = journalDb.journalDao

    //insertJournal is used in the ViewModel and communicates with the database
    override suspend fun insertJournal(journalItem: JournalItem) {
        journalDao.upsertJournalEntity(journalItem.toJournalEntity())
    }

    //overrides deleteJournal to delete a journal entry and give the info to the db
    override suspend fun deleteJournal(journalItem: JournalItem) {
        journalDao.deleteJournalEntity(journalItem.toJournalEntity())
    }

    //get a special journal entry by their id
    override suspend fun getJournalById(id: Int): JournalItem? {
        return journalDao.getJournalEntityById(id)?.toJournalItem()
    }

    //get all journal entries for a list
    override suspend fun getAllJournals(): List<JournalItem> {
        return journalDao.getAllJournalEntities().map { it.toJournalItem()}
    }

    //update a journal entry
    override suspend fun updateJournal(journalItem: JournalItem) {
        journalDao.upsertJournalEntity(journalItem.toJournalEntity())
    }

}