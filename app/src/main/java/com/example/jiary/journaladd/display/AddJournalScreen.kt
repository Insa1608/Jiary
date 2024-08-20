package com.example.jiary.journaladd.display

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jiary.R
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddJournalScreen(
    onSave: () -> Unit,
    journalId: Int?,
    addJournalViewModel: AddJournalViewModel = hiltViewModel()
){
    val addJournalState  by addJournalViewModel.addJournalState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(journalId) {
        if (journalId != null){
            addJournalViewModel.loadJournal(journalId)
        }
    }

    LaunchedEffect(true) {
        addJournalViewModel.journalSavedFlow.collectLatest { saved ->
            if (saved) {
                onSave()
            } else {
                Toast.makeText(
                    context,
                    R.string.error_saving_note,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }


    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= System.currentTimeMillis()
        }
    })
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {
        DatePicker(
            state = datePickerState
        )
        Spacer(
            modifier = Modifier.height(
                32.dp
            )
        )

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = addJournalState.title,
            onValueChange = {
                addJournalViewModel.onAction(
                    AddJournalAction.UpdateTitle(it)
                )
            },
            label = {
                Text(text = stringResource(R.string.title))
            },
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = addJournalState.entry,
            onValueChange = {
                addJournalViewModel.onAction(
                    AddJournalAction.UpdateEntry(it)
                )
            },
            label = {
                Text(text = stringResource(R.string.description))
            },
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = {
                addJournalViewModel.onAction(
                    AddJournalAction.SaveJournal
                )
            }
        ) {
            Text(
                text = stringResource(R.string.save),
                fontSize = 17.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }

}

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(millis))
}



