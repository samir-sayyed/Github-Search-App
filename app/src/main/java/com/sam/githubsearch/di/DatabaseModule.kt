package com.sam.githubsearch.di

import android.content.Context
import androidx.room.Room
import com.sam.githubsearch.data.local.AppDatabase
import com.sam.githubsearch.data.local.ProfileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "profile_database").build()


    @Singleton
    @Provides
    fun provideArticleDao(db: AppDatabase): ProfileDao = db.getProfileDao()

}