package com.example.jiary.base.dependencyinjection

import android.app.Application
import androidx.room.Room
import com.example.jiary.base.data.local.JournalDb
import com.example.jiary.base.data.repo.JournalRepoImplementation
import com.example.jiary.base.domain.model.repo.JournalRepo
import com.example.jiary.journal_list.domain.usecase.DeleteJournal
import com.example.jiary.journal_list.domain.usecase.GetAllJournals
import com.example.jiary.journalupdate.cases.UpdateJournal
import com.example.jiary.journaladd.cases.InsertJournal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule{
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
    fun provideAllJournalCases(
        journalRepo: JournalRepo
    ): GetAllJournals {
        return GetAllJournals(journalRepo)
    }

    @Provides
    @Singleton
    fun provideDeleteJournalCases(
        journalRepo: JournalRepo
    ): DeleteJournal {
        return DeleteJournal(journalRepo)
    }

    @Provides
    @Singleton
    fun provideInsertJournalCases(
        journalRepo: JournalRepo
    ): InsertJournal {
        return InsertJournal(journalRepo)
    }

    @Provides
    @Singleton
    fun provideUpdateJournalCases(
        journalRepo: JournalRepo
    ): UpdateJournal {
        return UpdateJournal(journalRepo)
    }

}