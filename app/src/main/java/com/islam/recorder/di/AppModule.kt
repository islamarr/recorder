package com.islam.recorder.di

import android.content.Context
import com.islam.recorder.data.db.AppDatabase
import com.islam.recorder.data.repositories.main.DefaultMainRepository
import com.islam.recorder.data.repositories.main.MainRepository
import com.islam.recorder.data.repositories.recording.DefaultRecordRepository
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
    fun provideMainRepository(db: AppDatabase) =
        DefaultMainRepository(db) as MainRepository

    @Singleton
    @Provides
    fun provideRecordRepository(db: AppDatabase) =
        DefaultRecordRepository(db) as RecordRepository


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.invoke(appContext)
    }


}

















