package com.ihridoydas.simpleapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
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

//------------------------------------------------------
    //Notification
    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Location",
                NotificationManager.IMPORTANCE_HIGH,
            )
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "location"
    }
    //------------------------------------------------------
}

//For WorkManager
class CustomWorkerFactory @Inject constructor(private val api: DemoApi): WorkerFactory(){
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker  = CustomWorker(api,appContext,workerParameters)
}