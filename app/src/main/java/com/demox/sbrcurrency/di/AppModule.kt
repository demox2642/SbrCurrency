package com.demox.sbrcurrency.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule{
    @Singleton
    @Provides
    fun provideConnectionManager(@ApplicationContext appContext: Context) =
        ConnectionManager(appContext)
}
