package com.example.jiary.journal_list.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jiary.core.data.repo.FakeJournalRepoUnitTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * test checks if it returns false when no title is added to the journal entry
 */

class InsertJournalTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var fakeJournalRepositoryImpl: FakeJournalRepoUnitTest
    private lateinit var insertJournal: InsertJournal

    @Before
    fun setUp() {
        fakeJournalRepositoryImpl = FakeJournalRepoUnitTest()
        insertJournal = InsertJournal(fakeJournalRepositoryImpl)
    }

    @Test
    fun `insert note without title, return false`() = runTest {
        val isInserted = insertJournal.invoke(
            title = "",
            entry = "text",
            date = 1
        )
        assertThat(isInserted).isFalse()
    }
}