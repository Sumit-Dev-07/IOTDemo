package com.hexalitics.connec8.di.hilt

import android.content.Context
import com.hexalitics.mvvm_starter.utils.MyApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * This method used for provide KadeApp instance to Hilt DI
     */
    @Singleton
    @Provides
    fun provideApp(@ApplicationContext context: Context) = context.applicationContext as MyApp



}