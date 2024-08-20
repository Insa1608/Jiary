package com.example.jiary.journal_list.display

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.Image
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.example.jiary.R
import com.example.jiary.base.display.FirstScreen
import com.example.jiary.base.display.util.Tags
import com.example.jiary.base.domain.model.JournalItem


@Composable
fun JournalScreen(
    onNavigationToAddJournal: (Int?) -> Unit,
    journalListViewModel: JournalViewModel = hiltViewModel(),
) {
    LaunchedEffect(true) {
        journalListViewModel.loadJournal()
    }

    val journalListState by journalListViewModel.journalListState.collectAsState()
    val orderByTitleState by journalListViewModel.orderByTitleState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.journal, journalListState.size),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .clickable {
                            journalListViewModel.changeOrder()
                        }
                        .padding(horizontal = 4.dp)
                ) {
                    Text(
                        text = if (orderByTitleState) stringResource(R.string.t)
                        else stringResource(R.string.d), textAlign = TextAlign.Start, fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(imageVector = Icons.AutoMirrored.Filled.Sort, contentDescription = if (orderByTitleState) {
                            stringResource(R.string.sort_by_date)
                        } else {
                            stringResource(R.string.sort_by_title)
                        })
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
                onClick = {onNavigationToAddJournal(null) }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_a_journal_entry)
                )
            }
        }
    ) { paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(
                count = journalListState.size,
                key = { it }
            ) {
                index ->
                ListJournalItem(
                    onDelete = {
                        journalListViewModel.deleteJournal(journalListState[index])
                    },
                    onEdit = {
                        onNavigationToAddJournal(journalListState[index].id)
                    },
                    journalItem = journalListState[index]
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

    }

}

@Composable
fun ListJournalItem(
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    journalItem: JournalItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 8.dp)
            .clip(
                RoundedCornerShape(20.dp)
            )
            .background(MaterialTheme.colorScheme.primary)
            .padding(8.dp)
    ){
        Image(
            modifier = Modifier
                .height(200.dp)
                .width(100.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primaryContainer),
            painter = painterResource(id = R.drawable.images),
            contentDescription = journalItem.title,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column (
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(vertical = 8.dp)
        ){

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = journalItem.title,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.clickable { onEdit() }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = journalItem.entry,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 15.sp,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
        }

        Icon(
            modifier = Modifier.clickable { onDelete() },
            imageVector = Icons.Default.Clear,
            contentDescription = journalItem.title,
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }

}