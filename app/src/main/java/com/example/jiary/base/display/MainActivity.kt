package com.example.jiary.base.display

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import com.example.jiary.base.display.ui.theme.JiaryTheme
import com.example.jiary.journal_list.display.JournalScreen
import com.example.jiary.journaladd.display.AddJournalScreen
import com.example.jiary.journalupdate.display.UpdateJournalScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JiaryTheme {
                Navigation()
            }
        }
    }

    @Composable
    fun Navigation(modifier: Modifier = Modifier){
        val navController = rememberNavController()

        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = FirstScreen.JournalList
        ) {
            composable<FirstScreen.JournalList> {
                JournalScreen(
                    onNavigationToAddJournal = {
                        navController.navigate(FirstScreen.AddJournal)
                    },
                    onNavigateToJournalUpdate = {
                        navController.navigate(FirstScreen.UpdateJournal)
                    }
                )
            }
            composable<FirstScreen.AddJournal> {
                AddJournalScreen(
                    onSave = {
                        navController.popBackStack()
                    }
                )
            }
            composable<FirstScreen.UpdateJournal> {
                UpdateJournalScreen(
                    onSave = {
                        navController.popBackStack()
                    }
                    )
            }

        }

    }
}