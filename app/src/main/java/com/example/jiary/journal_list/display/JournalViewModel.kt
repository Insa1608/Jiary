package com.example.jiary.journal_list.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jiary.base.domain.model.JournalItem
import com.example.jiary.journal_list.domain.usecase.JournalUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class JournalViewModel @Inject constructor(
    private val journalUseCases: JournalUseCases
): ViewModel() {

    private val _journalListState = MutableStateFlow<List<JournalItem>>(emptyList())
    val journalListState = _journalListState.asStateFlow()

    private val _orderByTitleState = MutableStateFlow(false)
    val orderByTitleState = _orderByTitleState.asStateFlow()
    //loads journal entries ordered by title
    fun loadJournal(){
        viewModelScope.launch{
            _journalListState.update {
                journalUseCases.getAllJournals.invoke(orderByTitleState.value)
            }
        }

    }
    //calls the use case for deleting a journal entry
    fun deleteJournal(journalItem: JournalItem){
        viewModelScope.launch{
            journalUseCases.deleteJournal.invoke(journalItem)
            loadJournal()
        }

    }
    //calls the function to change the order of the journal entries
    fun changeOrder() {
        viewModelScope.launch {
            _orderByTitleState.update { !it }
            loadJournal()
        }
    }
}