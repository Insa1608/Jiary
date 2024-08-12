package com.example.jiary.journalupdate.cases

import com.example.jiary.base.domain.model.JournalItem
import com.example.jiary.base.domain.model.repo.JournalRepo

class UpdateJournal(
    private val journalRepo: JournalRepo
) {
    suspend operator fun invoke(journal: JournalItem) {
        journalRepo.updateJournal(journal)
    }
}