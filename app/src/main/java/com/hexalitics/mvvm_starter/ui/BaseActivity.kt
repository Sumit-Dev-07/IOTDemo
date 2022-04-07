package com.hexalitics.mvvm_starter.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.hexalitics.mvvm_starter.di.NoConnectionInterceptor
import javax.inject.Inject

/**
 * Created by rahul on 05-04-2022
 */
open class BaseActivity: AppCompatActivity() {

    @Inject
    lateinit var noConnectionInterceptor: NoConnectionInterceptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}