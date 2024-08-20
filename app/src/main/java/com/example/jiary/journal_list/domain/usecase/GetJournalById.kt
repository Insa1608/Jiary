package com.example.jiary.journal_list.domain.usecase

import com.example.jiary.base.domain.model.JournalItem
import com.example.jiary.base.domain.model.repo.JournalRepo

class GetJournalById(
    private val journalRepo: JournalRepo
){
    suspend operator fun invoke(journalId: Int): JournalItem? {
        return journalRepo.getJournalById(journalId)
    }
}