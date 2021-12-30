package com.xxmukulxx.notes.di

import android.app.Application
import androidx.room.Room
import com.xxmukulxx.notes.feature_login.data.data_source.UserDatabase
import com.xxmukulxx.notes.feature_login.data.repository.UserDataRepositoryImpl
import com.xxmukulxx.notes.feature_login.domain.repository.UserDataRepository
import com.xxmukulxx.notes.feature_login.domain.use_cases.DeleteUser
import com.xxmukulxx.notes.feature_login.domain.use_cases.GetUser
import com.xxmukulxx.notes.feature_login.domain.use_cases.InsertUser
import com.xxmukulxx.notes.feature_login.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.feature_note.data.data_source.NoteDatabase
import com.xxmukulxx.notes.feature_note.data.repository.NoteRepositoryImpl
import com.xxmukulxx.notes.feature_note.domain.repository.NoteRepository
import com.xxmukulxx.notes.feature_note.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {


    @Provides
    @Singleton
    fun providesNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun providesNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun providesNoteUseCases(repo: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNote = GetNote(repo),
            getNotes = GetNotes(repo),
            deleteNote = DeleteNote(repo),
            insertNote = InsertNote(repo)
        )
    }


    @Provides
    @Singleton
    fun providesUserDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            UserDatabase.DATABASE_NAME
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun providesUserRepository(db: UserDatabase): UserDataRepository {
        return UserDataRepositoryImpl(db.userDao)
    }

    @Provides
    @Singleton
    fun providesUserUseCases(repo: UserDataRepository): UserUseCases {
        return UserUseCases(
            getUser = GetUser(repo),
            insertUser = InsertUser(repo),
            deleteUser = DeleteUser(repo),
        )
    }
}