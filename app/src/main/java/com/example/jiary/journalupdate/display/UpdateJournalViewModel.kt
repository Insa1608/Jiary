package com.example.jiary.journalupdate.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiary.journalupdate.cases.UpdateJournal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateJournalViewModel (
    private val updateJournal: UpdateJournal
) : ViewModel() {
        private val _updateJournalState = MutableStateFlow(UpdateJournalState())
        val updateJournalState = _updateJournalState.asStateFlow()

        private val _journalSavedChannel = Channel<Boolean>()
        val journalSavedFlow = _journalSavedChannel.receiveAsFlow()

        fun onAction(action: UpdateJournalAction) {
            when (action) {
                is UpdateJournalAction.UpdateTitle -> {
                    _updateJournalState.update {
                        it.copy(title = action.newTitle)
                    }
                }
                is UpdateJournalAction.UpdateEntry -> {
                    _updateJournalState.update {
                        it.copy(entry = action.newEntry)
                    }
                }

                UpdateJournalAction.SaveJournal -> {
                    viewModelScope.launch {
                        val isSaved = updateJournal(
                            title = updateJournalState.value.title,
                            entry = updateJournalState.value.entry)
                        _journalSavedChannel.send(isSaved)
                    }
                }

            }
        }

        suspend fun updateJournal(
            title: String,
            entry: String): Boolean {
            return updateJournal.invoke(
                title = title,
                entry = entry
            )
        }


    }
}