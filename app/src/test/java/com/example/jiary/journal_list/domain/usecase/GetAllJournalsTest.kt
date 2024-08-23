package com.example.jiary.journal_list.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jiary.core.data.repo.FakeJournalRepoUnitTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * test checks if an empty list is returned when there are no journal entries
 * and if the the sorting function works correct
 */

class GetAllJournalsTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var fakeJournalRepoUnitTest: FakeJournalRepoUnitTest
    private lateinit var getAllJournals: GetAllJournals

    @Before
    fun setUp() {
        fakeJournalRepoUnitTest = FakeJournalRepoUnitTest()
        getAllJournals = GetAllJournals(fakeJournalRepoUnitTest)

        fakeJournalRepoUnitTest.shouldHaveFilled(true)
    }

    @Test
    fun `get journals from an empty list`() = runTest{
        fakeJournalRepoUnitTest.shouldHaveFilled(false)

        val journal = getAllJournals.invoke(true)
        assertThat(journal.isEmpty())
    }

    @Test
    fun `get journal sort by title, sorted by title`() = runTest {
        fakeJournalRepoUnitTest.shouldHaveFilled(true)

        val journal = getAllJournals.invoke(true)

        for (i in 0 .. journal.size - 2) {
            assertThat(journal[i].title).isLessThan(journal[i + 1].title)
        }


    }
}