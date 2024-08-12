package com.example.jiary.base.display

sealed interface FirstScreen{
    @kotlinx.serialization.Serializable
    data object JournalList : FirstScreen

    @kotlinx.serialization.Serializable
    data object AddJournal : FirstScreen

    @kotlinx.serialization.Serializable
    data object UpdateJournal : FirstScreen

}