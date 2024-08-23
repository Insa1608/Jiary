package com.example.jiary.journal_list.domain.usecase

import com.example.jiary.base.domain.model.JournalItem
import com.example.jiary.base.domain.model.repo.JournalRepo

//class for updating an existing journal entry.
//to guarantee that the correct entry is chosen the journalId is needed here
class UpdateJournal(
    private val journalRepo: JournalRepo
) {

    suspend operator fun invoke(
        title: String,
        entry: String,
        date: Long,
        journalId: Int?
    ): Boolean {
        if (title.isEmpty() || entry.isEmpty()) {
            return false
        }
        val journal = JournalItem(
            id = journalId ?: 0,
            title = title,
            entry = entry,
            date = date
        )

        journalRepo.insertJournal(journal)
        return true
    }

}