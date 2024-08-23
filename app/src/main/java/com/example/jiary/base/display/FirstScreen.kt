package com.example.jiary.base.display

/**
 * interface to support the building of the routes between the different screens
 * in the MainActivity file
 */

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

