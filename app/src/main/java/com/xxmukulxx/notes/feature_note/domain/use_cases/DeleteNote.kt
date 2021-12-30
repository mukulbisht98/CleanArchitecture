package com.xxmukulxx.notes.feature_note.domain.use_cases

import com.xxmukulxx.notes.feature_note.domain.model.Note
import com.xxmukulxx.notes.feature_note.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}