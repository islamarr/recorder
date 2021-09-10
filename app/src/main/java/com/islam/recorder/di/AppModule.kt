package com.islam.recorder.di

import android.content.Context
import com.islam.recorder.data.db.AppDatabase
import com.islam.recorder.data.db.daos.ClipDao
import com.islam.recorder.data.repositories.main.MainRepositoryImpl
import com.islam.recorder.data.repositories.main.MainRepository
import com.islam.recorder.data.repositories.recording.RecordRepositoryImpl
import com.islam.recorder.data.repositories.recording.RecordRepository
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
    fun provideMainRepository(clipDao: ClipDao) =
        MainRepositoryImpl(clipDao) as MainRepository

    @Provides
    @Singleton
    fun provideClipDao(db: AppDatabase): ClipDao = db.getClipDao()

    @Singleton
    @Provides
    fun provideRecordRepository(clipDao: ClipDao) =
        RecordRepositoryImpl(clipDao) as RecordRepository

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase =
        AppDatabase.invoke(appContext)

}

















