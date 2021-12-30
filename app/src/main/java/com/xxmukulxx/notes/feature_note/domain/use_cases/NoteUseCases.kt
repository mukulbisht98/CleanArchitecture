package com.xxmukulxx.notes.feature_note.domain.use_cases

data class NoteUseCases(
    val getNotes: GetNotes,
    val getNote: GetNote,
    val deleteNote: DeleteNote,
    val insertNote: InsertNote
)