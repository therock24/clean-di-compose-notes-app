package com.therock24.cleanarch_di_notesapp.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.therock24.cleanarch_di_notesapp.feature_note.data.repository.FakeNoteRepository
import com.therock24.cleanarch_di_notesapp.feature_note.domain.model.InvalidNoteException
import com.therock24.cleanarch_di_notesapp.feature_note.domain.model.Note
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddNoteTest {

    private lateinit var fakeRepository: FakeNoteRepository
    private lateinit var addNote: AddNote

    @Before
    fun setUp() {
        fakeRepository = FakeNoteRepository()
        addNote = AddNote(repository = fakeRepository)
    }

    @Test
    fun `add note empty title, return exception`() = runBlocking {
        val note = Note(title = "", content = "abc", timestamp = 1, color = 1, id = 1)

        try {
            addNote.invoke(note)
            assert(false)
        } catch (e: InvalidNoteException) {
            assertThat(e.message).isEqualTo("The title of the note can't be empty")
        }
    }

    @Test
    fun `add note empty content, return exception`() = runBlocking {
        val note = Note(title = "abc", content = "", timestamp = 1, color = 1, id = 1)

        try {
            addNote.invoke(note)
            assert(false)
        } catch (e: InvalidNoteException) {
            assertThat(e.message).isEqualTo("The content of the note can't be empty")
        }
    }

    @Test
    fun `add valid note, no exception`() = runBlocking {
        val note = Note(title = "abc", content = "123", timestamp = 1, color = 1, id = 1)

        try {
            addNote.invoke(note)
            assert(true)
        } catch (e: InvalidNoteException) {
            assert(false)
        }
    }


}