package com.example.jiary.journalupdate.display


sealed interface UpdateJournalAction {
    data class UpdateTitle(val newTitle: String) : UpdateJournalAction
    data class UpdateEntry(val newEntry: String) : UpdateJournalAction
    data object SaveJournal: UpdateJournalAction
}