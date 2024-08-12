package com.example.jiary.base.data.repo


import com.example.jiary.base.data.Mapper.toJournalEntityForDelete
import com.example.jiary.base.data.Mapper.toJournalEntityForInsert
import com.example.jiary.base.data.Mapper.toJournalItem
import com.example.jiary.base.data.Mapper.toJournalEntityForChange
import com.example.jiary.base.data.local.JournalDb
import com.example.jiary.base.domain.model.JournalItem
import com.example.jiary.base.domain.model.repo.JournalRepo



class JournalRepoImplementation(
    journalDb: JournalDb
): JournalRepo{

    private val journalDao = journalDb.journalDao

    override suspend fun insertJournal(journalItem: JournalItem) {
        journalDao.upsertJournalEntity(journalItem.toJournalEntityForInsert())
    }

    override suspend fun deleteJournal(journalItem: JournalItem) {
        journalDao.deleteJournalEntity(journalItem.toJournalEntityForDelete())
    }

    override suspend fun updateJournal(journalItem: JournalItem){
        journalDao.updateJournalEntity(journalItem.toJournalEntityForChange())
    }

    override suspend fun getAllJournals(): List<JournalItem> {
        return journalDao.getAllJournalEntities().map { it.toJournalItem()}
    }

}