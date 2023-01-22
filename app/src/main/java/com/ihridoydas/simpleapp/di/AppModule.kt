package com.ihridoydas.simpleapp.di

import android.content.Context
import com.ihridoydas.simpleapp.data.local.PrefDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //Preference DataStore
    @Singleton
    @Provides
    fun providePreferencesDatastore(@ApplicationContext appContext: Context): PrefDataStore = PrefDataStore(appContext)
}