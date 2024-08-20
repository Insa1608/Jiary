package com.example.jiary.base.display

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import com.example.jiary.base.display.ui.theme.JiaryTheme
import com.example.jiary.journal_list.display.JournalScreen
import com.example.jiary.journaladd.display.AddJournalScreen

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
            startDestination = FirstScreen.JournalList.route
        ) {
            composable(FirstScreen.JournalList.route) {
                JournalScreen(
                    onNavigationToAddJournal = { journalId ->
                        val route = FirstScreen.AddJournal(journalId).route
                        navController.navigate(route)
                    }
                )
            }
            composable(
                route = "addJournal/{journalId}",
                arguments = listOf(navArgument("journalId") { type = NavType.StringType })
            ) { backStackEntry ->
                val journalId = backStackEntry.arguments?.getString("journalId")?.let {
                    if (it == "new") null else it.toInt()
                }
                AddJournalScreen(
                    journalId = journalId,
                    onSave = {
                        navController.popBackStack()
                    }
                )

            }
        }

    }
}

