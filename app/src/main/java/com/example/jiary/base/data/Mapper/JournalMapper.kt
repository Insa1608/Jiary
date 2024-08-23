package com.example.jiary.base.data.Mapper

import com.example.jiary.base.data.local.JournalEntity
import com.example.jiary.base.domain.model.JournalItem
/**
 * wrapper function from the data/local/JournalEntity to the domain/Model/JournalItem we want to use
 * returns JournalEntity
 */
fun JournalItem.toJournalEntity(
): JournalEntity {
    return JournalEntity(
        title = title,
        entry = entry,
        date = date,
        id = id
    )
}

/**
 * wrapper function from the data/local/JournalEntity to the domain/Model/JournalItem we want to use
 * the id is automatically set by the autogenerate property which is set to true in JournalEntity
 */
fun JournalEntity.toJournalItem(): JournalItem {
    return JournalItem(
        title = title,
        entry = entry,
        date = date,
        id = id
    )
}
