package com.example.jiary.journaladd.cases

import com.example.jiary.base.domain.model.JournalItem
import com.example.jiary.base.domain.model.repo.JournalRepo

class InsertJournal(
    private val journalRepo: JournalRepo
) {

    suspend operator fun invoke(
        title: String,
        entry: String
    ): Boolean {
        if (title.isEmpty() || entry.isEmpty()) {
            return false
        }
        val journal = JournalItem(
            title = title,
            entry = entry,
            date = System.currentTimeMillis()
        )

        journalRepo.insertJournal(journal)
        return true
    }

}