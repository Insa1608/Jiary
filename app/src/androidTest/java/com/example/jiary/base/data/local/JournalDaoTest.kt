package com.example.jiary.base.data.local


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.jiary.base.dependencyinjection.AppModule
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
@SmallTest
//The Modules are uninstalled so that the test app only uses the in memory database and not the same
//database that we use in the real app
@UninstallModules(AppModule::class)
class JournalDaoTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Inject
    lateinit var journalDb: JournalDb
    private lateinit var dao: JournalDao

    @Before
    fun setUp(){
        hiltRule.inject()
        dao = journalDb.journalDao
    }

    @After
    fun tearDown() {
        journalDb.close()
    }

    @Test
    fun getAllJournalFromEmptyDB_journalListIsEmpty() = runTest {
        assertThat(
            dao.getAllJournalEntities().isEmpty()
        )
    }

    @Test
    fun getAllJournalFromDB_journalListIsNotEmpty() = runTest {
        for (i in 1 .. 4  ) {
            val journalEntity = JournalEntity(
                id = i,
                title = "title $i",
                entry = "entry $i",
                image = "image $i",
                date = System.currentTimeMillis()
            )
            dao.upsertJournalEntity(journalEntity)
        }

        assertThat(
            dao.getAllJournalEntities().isNotEmpty()
        )
    }

    @Test
    fun insertJournal_journalIsInserted() = runTest {
        val journalEntity = JournalEntity(
            id = 1,
            title = "title",
            entry = "entry",
            image = "image",
            date = System.currentTimeMillis()
        )

        dao.upsertJournalEntity(journalEntity)

        assertThat(
            dao.getAllJournalEntities().contains(journalEntity)
        )
    }

    @Test
    fun deleteJournal_journalIsDeleted() = runTest {
        val journalEntity = JournalEntity(
            id = 1,
            title = "title",
            entry = "entry",
            image = "image",
            date = System.currentTimeMillis()
        )

        dao.upsertJournalEntity(journalEntity)

        assertThat(
            dao.getAllJournalEntities().contains(journalEntity)
        ).isTrue()

        dao.deleteJournalEntity(journalEntity)

        assertThat(
            !dao.getAllJournalEntities().contains(journalEntity)
        ).isTrue()
    }
}