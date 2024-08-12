package com.example.jiary.journal_list.domain.usecase

import com.example.jiary.base.domain.model.JournalItem
import com.example.jiary.base.domain.model.repo.JournalRepo

class GetAllJournals(
    private val journalRepo: JournalRepo
) {
    suspend operator fun invoke(
        isOrderByTitle: Boolean
    ): List<JournalItem> {
        return if (isOrderByTitle) {
            journalRepo.getAllJournals().sortedBy { it.title.lowercase() }
        } else {
            journalRepo.getAllJournals().sortedBy { it.date }
        }
    }
}