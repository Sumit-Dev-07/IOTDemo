package com.hexalitics.mvvm_starter.di.hilt

import android.app.Activity
import com.hexalitics.mvvm_starter.ui.BaseActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    /**
     * This method used for provide BaseActivity instance to Hilt DI
     */
    @Provides
    fun provideBaseActivity(activity: Activity): BaseActivity {
        return activity as BaseActivity
    }
}