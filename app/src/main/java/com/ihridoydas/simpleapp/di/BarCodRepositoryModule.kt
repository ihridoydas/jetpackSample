package com.ihridoydas.simpleapp.di

import com.ihridoydas.simpleapp.data.repository.barCode.BarCodeRepoImpl
import com.ihridoydas.simpleapp.domain.barCode.BarCodeMainRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class BarCodRepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMainRepo(
        mainRepoImpl : BarCodeRepoImpl
    ) : BarCodeMainRepo


}