package com.example.jiary.base.domain.model.repo


import com.example.jiary.base.domain.model.JournalItem

interface JournalRepo {

    suspend fun insertJournal(journalItem: JournalItem)

    suspend fun updateJournal(journalItem: JournalItem)

    suspend fun deleteJournal(journalItem: JournalItem)

    suspend fun getJournalById(id: Int): JournalItem?

    suspend fun getAllJournals(): List<JournalItem>
}