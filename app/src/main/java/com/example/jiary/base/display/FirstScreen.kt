package com.example.jiary.base.display

sealed interface FirstScreen {
    val route: String

    @kotlinx.serialization.Serializable
    data object JournalList : FirstScreen {
        override val route = "journalList"
    }

    @kotlinx.serialization.Serializable
    data class AddJournal(val journalId: Int? = null) : FirstScreen {
        override val route: String = "addJournal/${journalId ?: "new"}"
    }
}

