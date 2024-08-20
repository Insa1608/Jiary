package com.example.jiary.journal_list.domain.usecase

data class JournalUseCases (
    val getJournalById: GetJournalById,
    val getAllJournals: GetAllJournals,
    val deleteJournal: DeleteJournal,
    val insertJournal: InsertJournal,
    val updateJournal: UpdateJournal
    )