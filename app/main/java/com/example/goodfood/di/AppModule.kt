package com.example.goodfood.di

import android.content.Context
import com.example.goodfood.data.repository.HomeRepositoryImpl
import com.example.goodfood.data.repository.LoginRepositoryImpl
import com.example.goodfood.data.repository.RegisterRepositoryImpl
import com.example.goodfood.data.repository.UserDataRepositoryImpl
import com.example.goodfood.domain.repository.HomeRepository
import com.example.goodfood.domain.repository.LoginRepository
import com.example.goodfood.domain.repository.RegisterRepository
import com.example.goodfood.domain.repository.UserDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        @ApplicationContext context: Context
    ): LoginRepository = LoginRepositoryImpl(context = context)

    @Provides
    @Singleton
    fun provideRegisterRepository(): RegisterRepository = RegisterRepositoryImpl()

    @Provides
    @Singleton
    fun providesHomeRepository(): HomeRepository = HomeRepositoryImpl()

    @Provides
    @Singleton
    fun providesUserDataRepository(): UserDataRepository = UserDataRepositoryImpl()




}