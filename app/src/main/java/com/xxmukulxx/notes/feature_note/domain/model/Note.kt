package com.xxmukulxx.notes.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.Exception

@Entity
data class Note(
    val title: String,
    val content: String,
    val date: Long,
    val color: Int,
    @PrimaryKey(autoGenerate = true) val id: Int
)

class InvalidNoteException(message: String) : Exception(message)
