package com.example.jiary.base.dependencyinjection

import android.app.Application
import androidx.room.Room
import com.example.jiary.base.data.local.JournalDb
import com.example.jiary.base.data.repo.JournalRepoImplementation
import com.example.jiary.base.domain.model.repo.JournalRepo
import com.example.jiary.journal_list.domain.usecase.DeleteJournal
import com.example.jiary.journal_list.domain.usecase.GetAllJournals
import com.example.jiary.journal_list.domain.usecase.GetJournalById
import com.example.jiary.journal_list.domain.usecase.InsertJournal
import com.example.jiary.journal_list.domain.usecase.JournalUseCases
import com.example.jiary.journal_list.domain.usecase.UpdateJournal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

/**
 * The different functions are instantiated as a singleton to guarantee
 * that multiple instances are not created during the runtime
 */
object AppModule {
    @Provides
    @Singleton
    fun provideJournalDb(application: Application): JournalDb {
        return Room.databaseBuilder(
            application,
            JournalDb::class.java,
            "journal_db.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideJournalRepo(
        journalDb: JournalDb
    ): JournalRepo {
        return JournalRepoImplementation(journalDb)
    }

    @Provides
    @Singleton
    fun provideJournalUseCases(
        journalRepo: JournalRepo): JournalUseCases {
        return JournalUseCases(
            getAllJournals = GetAllJournals(journalRepo),
            getJournalById = GetJournalById(journalRepo),
            deleteJournal = DeleteJournal(journalRepo),
            insertJournal = InsertJournal(journalRepo),
            updateJournal = UpdateJournal(journalRepo)
        )
    }

}