package com.ihridoydas.simpleapp

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.ihridoydas.simpleapp.data.remote.workManager.DemoApi
import com.ihridoydas.simpleapp.features.workManager.CustomWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BaseApp : Application(), Configuration.Provider{
    //-----------
    //For WorkManager(Configaration)
    @Inject
    lateinit var workerFactory: CustomWorkerFactory
    override fun getWorkManagerConfiguration() =
       Configuration.Builder()
           .setMinimumLoggingLevel(Log.DEBUG)
           .setWorkerFactory(workerFactory)
           .build()

    //-----------
}

//For WorkManager
class CustomWorkerFactory @Inject constructor(private val api: DemoApi): WorkerFactory(){
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker  = CustomWorker(api,appContext,workerParameters)
}