package com.meow.hiltinjection.di

import android.app.Application
import com.meow.hiltinjection.data.remote.MyApi
import com.meow.hiltinjection.data.remote.MyRepository
import com.meow.hiltinjection.domain.repository.MyRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMyApi(): MyApi{
        return Retrofit.Builder()
            .baseUrl("https://test.com")
            .build().create(MyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMyRepository(api:MyApi,app:Application):MyRepository{
        return MyRepositoryImpl(api, appContext = app)
    }
}