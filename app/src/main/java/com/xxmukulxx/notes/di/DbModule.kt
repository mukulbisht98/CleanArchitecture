package com.xxmukulxx.notes.di

import android.app.Application
import androidx.room.Room
import com.xxmukulxx.notes.common.data.data_store.DataStore
import com.xxmukulxx.notes.common.data.data_store.vm.DataStoreViewModel
import com.xxmukulxx.notes.common.feature_app_db.AppDb
import com.xxmukulxx.notes.feature_login_signup.data.data_source.UserDao
import com.xxmukulxx.notes.feature_login_signup.data.repository.UserDataRepositoryImpl
import com.xxmukulxx.notes.feature_login_signup.domain.repository.UserDataRepository
import com.xxmukulxx.notes.feature_login_signup.domain.use_cases.DeleteUser
import com.xxmukulxx.notes.feature_login_signup.domain.use_cases.GetUser
import com.xxmukulxx.notes.feature_login_signup.domain.use_cases.InsertUser
import com.xxmukulxx.notes.feature_login_signup.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.feature_note.data.data_source.NoteDatabase
import com.xxmukulxx.notes.feature_note.data.repository.NoteRepositoryImpl
import com.xxmukulxx.notes.feature_note.domain.repository.NoteRepository
import com.xxmukulxx.notes.feature_note.domain.use_cases.*
import com.xxmukulxx.notes.feature_product.data.data_cource.ProductDao
import com.xxmukulxx.notes.feature_product.data.data_cource.repository.ProductDataRepositoryImpl
import com.xxmukulxx.notes.feature_product.domain.repository.ProductRepository
import com.xxmukulxx.notes.feature_product.domain.use_cases.*
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
    fun provideAppDatabase(app: Application): AppDb =
        Room.databaseBuilder(app, AppDb::class.java, AppDb.DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration().build()


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
    fun provideProductDao(appDb: AppDb): ProductDao = appDb.productDao()


    @Provides
    @Singleton
    fun providesProductUseCases(repo: ProductRepository): ProductUseCases = ProductUseCases(
        getProducts = GetProducts(repo),
        getSingleProduct = GetSingleProduct(repo),
        insertProduct = InsertProduct(repo),
        deleteProduct = DeleteProduct(repo),
        updateProduct = UpdateProduct(repo),
        searchProductFromDb = SearchProductFromDb(repo)
    )


    @Provides
    @Singleton
    fun providesProductRepository(dao: ProductDao): ProductRepository =
        ProductDataRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideUserDao(appDb: AppDb): UserDao = appDb.userDao()

    @Provides
    @Singleton
    fun providesUserRepository(dao: UserDao): UserDataRepository =
        UserDataRepositoryImpl(dao)


    @Provides
    @Singleton
    fun providesUserUseCases(repo: UserDataRepository): UserUseCases {
        return UserUseCases(
            getUser = GetUser(repo),
            insertUser = InsertUser(repo),
            deleteUser = DeleteUser(repo),
        )
    }


    @Provides
    @Singleton
    fun provideDataStoreVM(dataStore: DataStore): DataStoreViewModel {
        return DataStoreViewModel(dataStore)
    }
}