package com.example.jiary.base.data.Mapper

import com.example.jiary.base.data.local.JournalEntity
import com.example.jiary.base.domain.model.JournalItem

fun JournalItem.toJournalEntityForInsert(
): JournalEntity {
    return JournalEntity(
        title = title,
        entry = entry,
        date = date
    )
}

fun JournalItem.toJournalEntityForDelete(
): JournalEntity {
    return JournalEntity(
        title = title,
        entry = entry,
        date = date,
        id = id
    )
}

fun JournalItem.toJournalEntityForChange(
): JournalEntity {
    return JournalEntity(
        title = title,
        entry = entry,
        date = date,
        id = id
    )
}


//wrapper function from the data/local/JournalEntity to the domain/Model/JournalItem we want to use
//a default value of zero is set to the id just to be sure, but normally there should be set an id
//through the autogenerate property which is set to true in JournalEntity
fun JournalEntity.toJournalItem(): JournalItem {
    return JournalItem(
        title = title,
        entry = entry,
        date = date,
        id = id ?: 0
    )
}
