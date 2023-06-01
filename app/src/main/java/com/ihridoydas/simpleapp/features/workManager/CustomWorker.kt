package com.ihridoydas.simpleapp.features.workManager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.ihridoydas.simpleapp.data.remote.workManager.DemoApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.net.UnknownHostException

@HiltWorker
class CustomWorker @AssistedInject constructor(
    private val api: DemoApi,
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters
) : CoroutineWorker(context,workerParameters) {
    override suspend fun doWork(): Result {
        return try {
            val response = api.getPost()
            return if(response.isSuccessful){
                Log.d("CustomWorker", "Success!")
                Log.d("CustomWorker", "doWork: Id ${response.body()?.id} Title:${response.body()?.title}")
                Result.success()
            }else{
                Log.d("CustomWorker", "Retrying")
                Result.retry()
            }
        }catch (e:UnknownHostException){
            Log.d("CustomWorker", "Retrying")
            Result.retry()
        }catch (e:Exception){
            Log.d("CustomWorker", "Error!")
            Result.failure(Data.Builder().putString("error",e.toString()).build())
        }

    }

}