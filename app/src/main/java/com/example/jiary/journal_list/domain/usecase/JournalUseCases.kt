package com.example.jiary.journal_list.domain.usecase


/**
 * all use cases are summed up in this data class to guarantee an easier handling
 */
data class JournalUseCases (
    val getJournalById: GetJournalById,
    val getAllJournals: GetAllJournals,
    val deleteJournal: DeleteJournal,
    val insertJournal: InsertJournal,
    val updateJournal: UpdateJournal
    )