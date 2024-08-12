package com.example.jiary.journaladd.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiary.journaladd.cases.InsertJournal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddJournalViewModel @Inject constructor(
    private val insertJournal: InsertJournal
) : ViewModel() {
    private val _addJournalState = MutableStateFlow(AddJournalState())
    val addJournalState = _addJournalState.asStateFlow()

    private val _journalSavedChannel = Channel<Boolean>()
    val journalSavedFlow = _journalSavedChannel.receiveAsFlow()

    fun onAction(action: AddJournalAction) {
        when (action) {
            is AddJournalAction.UpdateTitle -> {
                _addJournalState.update {
                    it.copy(title = action.newTitle)
                }
            }
            is AddJournalAction.UpdateEntry -> {
                _addJournalState.update {
                    it.copy(entry = action.newEntry)
                }
            }

            AddJournalAction.SaveJournal -> {
                viewModelScope.launch {
                    val isSaved = insertJournal(
                        title = addJournalState.value.title,
                        entry = addJournalState.value.entry)
                    _journalSavedChannel.send(isSaved)
                }
            }

        }
    }

    suspend fun insertJournal(
        title: String,
        entry: String): Boolean {
        return insertJournal.invoke(
            title = title,
            entry = entry
        )
    }


}
