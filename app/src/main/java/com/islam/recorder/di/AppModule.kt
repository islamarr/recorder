package com.islam.recorder.di

import android.content.Context
import com.islam.data.db.AppDatabase
import com.islam.data.repositories.*
import com.islam.domain.repositories.MainRepository
import com.islam.domain.repositories.RecordRepository
import com.islam.domain.usecases.DeleteRecordUseCase
import com.islam.domain.usecases.GetAllRecordUseCase
import com.islam.domain.usecases.SaveRecordUseCase
import com.islam.domain.usecases.UndoClipUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMainRepository(mainLocalDataSource: MainLocalDataSource) =
        MainRepositoryImpl(mainLocalDataSource) as MainRepository

    @Singleton
    @Provides
    fun provideRecordRepository(recordLocalDataSource: RecordLocalDataSource) =
        RecordRepositoryImpl(recordLocalDataSource) as RecordRepository

    @Singleton
    @Provides
    fun provideMainLocalDataSource(db: AppDatabase): MainLocalDataSource =
        MainLocalDataSourceImpl(db)

    @Singleton
    @Provides
    fun provideRecordLocalDataSource(db: AppDatabase): RecordLocalDataSource =
        RecordLocalDataSourceImpl(db)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.invoke(appContext)
    }

    @Singleton
    @Provides
    fun provideSaveRecordUseCase(mainRepository: MainRepository): SaveRecordUseCase {
        return SaveRecordUseCase(mainRepository)
    }

    @Singleton
    @Provides
    fun provideGetAllRecordUseCase(recordRepository: RecordRepository): GetAllRecordUseCase {
        return GetAllRecordUseCase(recordRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteRecordUseCase(recordRepository: RecordRepository): DeleteRecordUseCase {
        return DeleteRecordUseCase(recordRepository)
    }

    @Singleton
    @Provides
    fun provideUndoClipUseCase(recordRepository: RecordRepository): UndoClipUseCase {
        return UndoClipUseCase(recordRepository)
    }

}

















