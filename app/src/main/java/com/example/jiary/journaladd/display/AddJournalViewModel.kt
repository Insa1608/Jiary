package com.example.jiary.journaladd.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiary.journal_list.domain.usecase.JournalUseCases
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
    private val journalUseCases: JournalUseCases
) : ViewModel() {
    private val _addJournalState = MutableStateFlow(AddJournalState())
    val addJournalState = _addJournalState.asStateFlow()

    private val _journalSavedChannel = Channel<Boolean>()
    val journalSavedFlow = _journalSavedChannel.receiveAsFlow()

    fun loadJournal(journalId: Int) {
        viewModelScope.launch {
            val journal = journalUseCases.getJournalById(journalId)
            if (journal != null) {
                _addJournalState.update { state ->
                    state.copy(
                        title = journal.title,
                        entry = journal.entry,
                        journalId = journalId
                    )
                }
            }
        }
    }

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
                        entry = addJournalState.value.entry,
                        journalId = addJournalState.value.journalId)
                    _journalSavedChannel.send(isSaved)
                }
            }

        }
    }

    suspend fun insertJournal(
        title: String,
        entry: String,
        journalId: Int?
    ): Boolean {
        val date = System.currentTimeMillis()
        return if (journalId == null) {
            journalUseCases.insertJournal.invoke(
                title = title,
                entry = entry,
                date = date
            )
        } else {
            journalUseCases.updateJournal.invoke(
                journalId = journalId,
                title = title,
                entry = entry,
                date = date
            )
        }
    }

}
