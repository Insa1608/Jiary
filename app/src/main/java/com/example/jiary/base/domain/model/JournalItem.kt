package com.example.jiary.base.domain.model

data class JournalItem (
    var title: String,
    var entry: String,
    var date: Long,
    val id: Int? = null
)
