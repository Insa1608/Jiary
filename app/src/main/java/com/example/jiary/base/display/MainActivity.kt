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

//define the entrypoint of the application and build a composable function
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

    //composable function to navigate between the screens unsing navHost
    @Composable
    fun Navigation(modifier: Modifier = Modifier){
        val navController = rememberNavController()

        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = FirstScreen.JournalList.route
        ) {
            //navigation from the starting screen to the second screen where a journal entry can be add
            composable(FirstScreen.JournalList.route) {
                JournalScreen(
                    onNavigationToAddJournal = { journalId ->
                        val route = FirstScreen.AddJournal(journalId).route
                        navController.navigate(route)
                    }
                )
            }
            //the defined route is used to navigate to the screen and when an id is presents the
            //existing entry is loaded from the stack
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

