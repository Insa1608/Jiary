package com.example.jiary.base.data.repo

import com.example.jiary.base.domain.model.JournalItem
import com.example.jiary.base.domain.model.repo.JournalRepo

class FakeJournalRepo: JournalRepo{
    private var journalItems = mutableListOf<JournalItem>()

    fun shouldHaveFilled(shouldHaveFilled: Boolean) {
        journalItems =
            if (shouldHaveFilled) {
                mutableListOf(
                    JournalItem("title 1", "entry 1", 1),
                    JournalItem("title 2", "entry 2", 2),
                    JournalItem("title 3", "entry 3", 3),
                    JournalItem("title 4", "entry 4", 4)
                )
            } else {
                mutableListOf()
            }
    }

    override suspend fun insertJournal(journalItem: JournalItem) {
        journalItems.add(journalItem)
    }

    override suspend fun deleteJournal(journalItem: JournalItem) {
        journalItems.remove(journalItem)
    }

    override suspend fun getAllJournals(): List<JournalItem> {
        return journalItems
    }

}