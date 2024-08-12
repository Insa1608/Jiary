package com.example.jiary.journal_list.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jiary.base.domain.model.JournalItem
import com.example.jiary.core.data.repo.FakeJournalRepoUnitTest
import kotlinx.coroutines.test.runTest
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class DeleteJournalTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var fakeJournalRepoUnitTest: FakeJournalRepoUnitTest
    private lateinit var deleteJournal: DeleteJournal

    @Before
    fun setUp() {
        fakeJournalRepoUnitTest = FakeJournalRepoUnitTest()
        deleteJournal = DeleteJournal(fakeJournalRepoUnitTest)
        fakeJournalRepoUnitTest.shouldHaveFilled(true)
    }

    @Test
    fun `delete journals from list`() = runTest {

        val journal = JournalItem("title 3", "entry 3", 3)

        deleteJournal.invoke(journal)

        assertThat(fakeJournalRepoUnitTest.getAllJournals().contains(journal)).isFalse()

    }

}