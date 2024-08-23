package com.example.jiary.base.domain.model

/**
 * data needed for a journal entry is defined here
 */
data class JournalItem (
    var title: String,
    var entry: String,
    var date: Long,
    val id: Int? = null
)
