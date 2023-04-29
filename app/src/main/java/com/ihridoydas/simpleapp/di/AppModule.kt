package com.ihridoydas.simpleapp.di

import android.app.Application
import android.content.Context
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.ihridoydas.simpleapp.data.local.PrefDataStore
import com.ihridoydas.simpleapp.data.repository.camera.CustomCameraRepo
import com.ihridoydas.simpleapp.data.repository.camera.CustomCameraRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //Preference DataStore
    @Singleton
    @Provides
    fun providePreferencesDatastore(@ApplicationContext appContext: Context): PrefDataStore = PrefDataStore(appContext)

    //Camera Provide
    @Provides
    @Singleton
    fun provideCameraSelector(): CameraSelector {
        return CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
    }

    @Provides
    @Singleton
    fun provideCameraProvider(application: Application)
            : ProcessCameraProvider {
        return ProcessCameraProvider.getInstance(application).get()

    }

    @Provides
    @Singleton
    fun provideCameraPreview(): Preview {
        return Preview.Builder().build()
    }

    @Provides
    @Singleton
    fun provideImageCapture(): ImageCapture {
        return ImageCapture.Builder()
            .setFlashMode(ImageCapture.FLASH_MODE_ON)
            .setTargetAspectRatio(AspectRatio.RATIO_16_9)
            .build()
    }

    @Provides
    @Singleton
    fun provideImageAnalysis(): ImageAnalysis {
        return ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
    }

    @Provides
    @Singleton
    fun provideCustomCameraRepo(
        cameraProvider: ProcessCameraProvider,
        selector: CameraSelector,
        imageCapture: ImageCapture,
        imageAnalysis: ImageAnalysis,
        preview: Preview
    ): CustomCameraRepo {
        return CustomCameraRepoImpl(
            cameraProvider,
            selector,
            preview,
            imageAnalysis,
            imageCapture
        )
    }

    //Bar Code Scanner
    //@ViewModelScoped
    @Provides
    @Singleton
    fun provideContext(app:Application):Context{
        return app.applicationContext
    }

   // @ViewModelScoped
    @Provides
    @Singleton
    fun provideBarCodeOptions() : GmsBarcodeScannerOptions {
        return GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
            .build()
    }

   // @ViewModelScoped
    @Provides
    @Singleton
    fun provideBarCodeScanner(context: Context,options: GmsBarcodeScannerOptions): GmsBarcodeScanner {
        return GmsBarcodeScanning.getClient(context, options)
    }

}