package com.example.jiary.journalupdate.display

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jiary.base.data.local.JournalEntity
import com.example.jiary.journaladd.display.AddJournalViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateJournalScreen(
    journalList: List<JournalEntity>,
    JournalId: String ?,
    onSave: () -> Unit,
    updateJournalViewModel: UpdateJournalViewModel = hiltViewModel()
){

}