package com.example.jiary.journal_list.domain.usecase

import com.example.jiary.base.domain.model.JournalItem
import com.example.jiary.base.domain.model.repo.JournalRepo

class InsertJournal(
    private val journalRepo: JournalRepo
) {

    suspend operator fun invoke(
        title: String,
        entry: String,
        date: Long
    ): Boolean {
        if (title.isEmpty() || entry.isEmpty()) {
            return false
        }
        val journal = JournalItem(
            title = title,
            entry = entry,
            date = date
        )

        journalRepo.insertJournal(journal)
        return true
    }

}