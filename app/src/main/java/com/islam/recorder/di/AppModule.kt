package com.islam.recorder.di

import android.content.Context
import com.islam.recorder.data.db.AppDatabase
import com.islam.recorder.data.repositories.DefaultMainRepository
import com.islam.recorder.data.repositories.MainRepository
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
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.invoke(appContext)
    }


}

















