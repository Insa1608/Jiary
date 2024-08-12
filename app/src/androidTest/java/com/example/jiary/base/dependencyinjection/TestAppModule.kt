package com.example.jiary.base.dependencyinjection

import android.app.Application
import androidx.room.Room
import com.example.jiary.base.data.local.JournalDb
import com.example.jiary.base.data.repo.FakeJournalRepo
import com.example.jiary.base.domain.model.repo.JournalRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

//for test issues I provide an in memory database. So when the app is closed
//all the data and the database in general is gone

object TestAppModule{
    @Provides
    @Singleton
    fun provideJournalDb(application: Application): JournalDb {
        return Room.inMemoryDatabaseBuilder(
            application,
            JournalDb::class.java
        ).build()
    }

    @Provides
    @Singleton
    fun provideJournalRepo(): JournalRepo {
        return FakeJournalRepo()
    }

}