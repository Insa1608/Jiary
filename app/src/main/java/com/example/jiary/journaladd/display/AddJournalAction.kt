package com.example.jiary.journaladd.display

sealed interface AddJournalAction{
    data class UpdateTitle(val newTitle: String) : AddJournalAction
    data class UpdateEntry(val newEntry: String) : AddJournalAction
    data object SaveJournal: AddJournalAction
}